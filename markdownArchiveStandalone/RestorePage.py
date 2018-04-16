from io import open

from wiki.web import ArchivePage
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

    def restore(self,pageName,commitID):
        self.cursor.execute("SELECT page_file FROM wiki.page WHERE page_name='"+pageName+"' AND commit_id="+commitID.__str__())
        rows = self.cursor.fetchall()
        with open(self.path, "w",encoding='utf-8') as file:
            for row in rows:
                str = row[0].__str__()
                file.write(unicode(str))
        file.close()
        name = self.fileName[:-3]
        arc = ArchivePage(name, self.path)
        arc.store()

'''
Test Code
'''
# arc = RestorePage("home", "/Users/liamtiemon/PycharmProjects/CSC440WikiWiki/content/home.md")
# arc.restore('home.md', 4)
# arc.cursor.execute("SELECT page_file FROM wiki.page WHERE page_file='home.md'")
# rows = arc.cursor.fetchall()
# for row in rows:
#     print " " + row[0].__str__()


