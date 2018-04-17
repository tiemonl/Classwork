from markdownArchiveStandalone.ArchiveDatabaseConnection import ArchiveDatabaseConnection


class HistoryPage():

    def __init__(self, page_name, page_commit, timestamp, user):
        self.page_name = page_name
        self.commit = page_commit
        self.timestamp = timestamp
        self.user = user

def displayHistoryConsole(page_name):
    cursor = ArchiveDatabaseConnection().conn.cursor()
    cursor.execute("Select DISTINCT(page_name) from wiki.page ORDER BY page_name")
    page_names = cursor.fetchall()
    cursor.execute("Select commit_id, page_name, date_update, user_last_update from wiki.page ORDER BY commit_id DESC")
    inner = cursor.fetchall()

    page_dict = {}
    for pg in page_names:
        page_dict[pg[0]] = []

    for pg in inner:
        h_pg = HistoryPage(pg[1],pg[0],pg[2],pg[3])
        page_dict[pg[1]].append(h_pg)

    for pg in page_dict[page_name]:
        print "Commit#: " + pg.commit.__str__() + " User:" + pg.user.__str__() + " Date: " + pg.timestamp.__str__()