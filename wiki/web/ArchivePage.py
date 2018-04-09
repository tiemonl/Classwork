from collections import OrderedDict
from io import open
import datetime
import os
import re

from flask import abort
from flask import url_for
import markdown

from wiki.web.ArchiveDatabaseConnection import ArchiveDatabaseConnection


class ArchivePage:
    def __init__(self, name, storing):
        self.path = "../../content/" + name
        self.file = open(self.path, "r")
        self.fileName = self.file.name
        self.contents = ""
        if storing:
            for line in self.file.readlines():
                self.contents +=  line.__str__()
            self.contentBytes = str.encode(self.contents)
            print(type(self.contentBytes))
            date = datetime.datetime.now()
            self.timestamp = date.month.__str__() + "/" + date.day.__str__() + "/" + date.year.__str__() + " " +\
                date.hour.__str__() + ":" + date.minute.__str__() + ":" + date.second.__str__()
            print(self.contents + "\n" + self.timestamp)
            #print(self.contentBytes)
        cursor = ArchiveDatabaseConnection().conn.cursor()
        print "Insert?"
        cursor.execute("INSERT INTO wiki.pages(page_id,commit_id,date_update,file_name, page_file)"
                       "VALUES((Select count(page_id) + 1 from wiki.pages),1, current_date, 'home.md', '{" + self.contentBytes +"}')")
        print "select statement?"
        cursor.execute("Select count(*) from wiki.pages")
        rows = cursor.fetchall()
        for row in rows:
            print " ", row[0]

    #def store(self):

    #def retrieve(self):

'''
Test Code
'''
ArchivePage("home.md", True);
