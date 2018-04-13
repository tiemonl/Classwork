from io import open


from wiki.web.ArchiveDatabaseConnection import ArchiveDatabaseConnection


class RestorePage:
    def __init__(self, name, url):
        self.fileName = name + ".md"
        self.path = url
        self.file = open(self.path, "r")
        self.contents = ""
        for line in self.file.readlines():
            self.contents +=  line.__str__()
        self.contentBytes = str.encode(self.contents)
        self.cursor = ArchiveDatabaseConnection().conn.cursor()

    def store(self):
        self.cursor.execute("Select page_name from wiki.page where page_name = '" + self.fileName + "'")
        if len(self.cursor.fetchall()) > 0:
            self.cursor.execute("INSERT INTO wiki.page(page_id,page_name,commit_id,date_update, page_file)"
                                " VALUES((SELECT DISTINCT page_id from wiki.page where page_name = '" + self.fileName + "'),"
                                " '" + self.fileName + "', (SELECT (count(commit_id) + 1) from wiki.page "
                                "where page_name = '" + self.fileName + "') "
                                ", current_date,  '" + self.contents + "')")
            self.cursor.execute("COMMIT")

        else:
            self.cursor.execute("INSERT INTO wiki.page(page_id,page_name,commit_id,date_update, page_file)"
                                "VALUES((SELECT COUNT(DISTINCT page_id) + 1 from wiki.page), '" + self.fileName + "',1, CURRENT_DATE,'"
                                + self.contents + "')")
            self.cursor.execute("COMMIT")

    def restore(self,pageName,commitID):
        self.cursor.execute("SELECT page_file FROM wiki.page WHERE page_name='"+pageName+"' AND commit_id="+commitID.__str__())
        rows = self.cursor.fetchall()
        with open(self.path, "w",encoding='utf-8') as file:
            for row in rows:
                str = row[0].__str__()
                file.write(unicode(str))
        file.close()

'''
Test Code
'''
arc = RestorePage("home", "/Users/liamtiemon/PycharmProjects/CSC440WikiWiki/content/home.md")
arc.restore('home.md', 4)
arc.cursor.execute("SELECT page_file FROM wiki.page WHERE page_file='home.md'")
rows = arc.cursor.fetchall()
for row in rows:
    print " " + row[0].__str__()


