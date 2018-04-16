from io import open
from flask import session


from wiki.web.ArchiveDatabaseConnection import ArchiveDatabaseConnection


class ArchivePage:
    def __init__(self, name, url):
        self.fileName = name + ".md"
        self.path = url
        self.file = open(self.path, "r")
        self.contents = ""
        for line in self.file.readlines():
            self.contents +=  line.__str__()
        self.contentBytes = str.encode(self.contents)
        self.cursor = ArchiveDatabaseConnection().conn.cursor()
        try:
            self.user_id = session.get('user_id')
        except:
            print "no valid session"
            self.user_id = None

    def store(self):
        self.cursor.execute("Select page_name from wiki.page where page_name = '" + self.fileName + "'")
        if len(self.cursor.fetchall()) > 0:
            self.cursor.execute("INSERT INTO wiki.page(page_id,page_name,commit_id,date_update, page_file,user_last_update)"
                                " VALUES((SELECT DISTINCT page_id from wiki.page where page_name = '" + self.fileName + "'),"
                                " '" + self.fileName + "', (SELECT (count(commit_id) + 1) from wiki.page "
                                "where page_name = '" + self.fileName + "') "
                                ", current_date,  '" + self.contents + "','" + self.user_id + "')")
            self.cursor.execute("COMMIT")

        else:
            self.cursor.execute("INSERT INTO wiki.page(page_id,page_name,commit_id,date_update, page_file, user_last_update)"
                                "VALUES((SELECT COUNT(DISTINCT page_id) + 1 from wiki.page), '" + self.fileName + "',1, CURRENT_DATE,'"
                                + self.contents + "','" + self.user_id + "')")
            self.cursor.execute("COMMIT")

'''
Test Code
'''
# arc = ArchivePage("home", "/Users/Ryan Guard/Desktop/CSC440WikiWiki/content/home.md")
# arc.cursor.execute("(SELECT user_last_update from wiki.page)")
# rows = arc.cursor.fetchall()
# for row in rows:
#     print " " + row[0].__str__() #+ " " + row[1].__str__() + " " + row[2].__str__()
