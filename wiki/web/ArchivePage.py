from io import open


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
        #self.cursor.execute("INSERT INTO wiki.pages(page_id,commit_id,date_update,file_name, page_file)"
        #                    "VALUES('500','1',CURRENT_DATE, 'yes.md', '{yes}')")
        #self.cursor.execute("COMMIT")

    def store(self):
        self.cursor.execute("Select file_name from wiki.pages")
        if self.cursor.fetchall()[0] == self.fileName:
            self.cursor.execute("INSERT INTO wiki.pages(page_id,commit_id,date_update,file_name, page_file)"
                       "VALUES((SELECT COUNT(page_id) + 1 from wiki.pages),1, CURRENT_DATE, \" + self.fileName + \"',"
                       " '{" + self.contents +"}')")
            self.cursor.execute("COMMIT")
        else:
            self.cursor.execute('INSERT INTO wiki.pages(page_id,commit_id,date_update,file_name, page_file)'
                       'VALUES((SELECT (MAX(page_id) + 1) from wiki.pages where file_name = "' + self.fileName + '"),'
                            ' (SELECT (count(commit_id) + 1) from wiki.pages where file_name = "' + self.fileName + '") '
                                ', current_date, "' + self.fileName + '", "{' + self.contents + '}")')
            self.cursor.execute("COMMIT")

'''
Test Code
'''
#arc = ArchivePage("yes")
#arc.store()
