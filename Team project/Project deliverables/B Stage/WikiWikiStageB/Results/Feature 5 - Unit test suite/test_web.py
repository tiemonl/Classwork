from tests import WikiBaseTestCase
from wiki.web.user import UserManager
from tests.ArchivePage import ArchivePage
from tests.RestorePage import RestorePage
import os


class WebContentTestCase(WikiBaseTestCase):
    """
        Various test cases around web content.
    """

    def test_index_missing(self):
        """
            Assert the wiki will correctly play the content missing
            index page, if no index page exists.
        """
        rsp = self.app.get('/')
        assert b"You did not create any content yet." in rsp.data
        assert rsp.status_code == 200


"""
Tests below are Unit tests created for stage C to get these tests to run you must make the Test File Path accurate.
This class tests the UserManager Class's functions
"""

TestFilePath = ''


class TestUserManager(WebContentTestCase):

    def setUp(self):
        super(TestUserManager, self).setUp()

    def test_read(self):
        m = UserManager(TestFilePath)
        self.assertIsNotNone(m.read)

    def test_add_user(self):
        m = UserManager(TestFilePath)
        self.assertIsNotNone(m.add_user('Ryan', 'pass', authentication_method='hash'))

    def test_get_user(self):
        m = UserManager(TestFilePath)
        self.assertIsNotNone(m.get_user('liam'))

    def test_delete_user(self):
        m = UserManager(TestFilePath)
        m.add_user('Ronnie', 'password', authentication_method='hash')
        self.assertTrue(m.delete_user('Ronnie'))

    def test_invalid_authentication_method(self):
        m = UserManager(TestFilePath)
        self.assertRaises(NotImplementedError, m.add_user, m, 'Elizabeth', 12345, authentication_method='this will cause an error')

    def tearDown(self):
        super(TestUserManager, self).tearDown()
        m = UserManager(TestFilePath)
        m.delete_user('Ryan')

"""
Tests below written as part of Stage B in conjunction with the database features
Dev: Ronnie Hoover (hooverr3)
"""
class TestArchivePage(WikiBaseTestCase):

    def setUp(self):
        super(TestArchivePage, self).setUp()
        self.page = ArchivePage("unittestwiki5589607.md", "unittestwiki5589607.md")

    def test_store(self):
        """
            Assert that a stored page has the same content as the local file
        """
        self.page.store()
        self.page.cursor.execute("Select page_file from wiki.page where page_name = '" + self.page.fileName + "'")
        self.assertEquals(open("unittestwiki5589607.md", "r").read(), self.page.cursor.fetchall()[0][0])

    def tearDown(self):
        super(TestArchivePage, self).tearDown()
        self.page.cursor.execute("Delete from wiki.page where page_name = '" + self.page.fileName + "'")


class TestRestorePage(WikiBaseTestCase):

    def setUp(self):
        super(TestRestorePage, self).setUp()
        self.page = RestorePage("unittestwiki5589607.md", "unittestwiki5589607.md")
        self.archPage = ArchivePage("unittestwiki5589607.md", "unittestwiki5589607.md")
        self.archPagev2 = ArchivePage("unittestwiki5589607.md", "unittestwiki5589607v2.md")

    def test_restore(self):
        """
            Assert that a restored page will have the anticipated content
        """
        self.archPage.store()
        self.archPagev2.store()
        pagecontent = open("unittestwiki5589607.md","r").read()
        self.page.restore(1)
        self.assertEquals(pagecontent, open("unittestwiki5589607.md","r").read())

    def test_restoreDeleted(self):
        """
            Assert that restoring a page that was deleted will recreate the page
        """
        self.archPage.store()
        page = RestorePage("unittestwiki5589607.md", "exampletext0091.md")
        page.restore(1)
        self.assertTrue(os.path.isfile("exampletext0091.md"))

    def tearDown(self):
        super(TestRestorePage, self).tearDown()
        self.archPage.cursor.execute("Delete from wiki.page where page_name = '" + self.page.fileName + "'")
        if os.path.isfile("exampletext0091.md"):
            os.remove("exampletext0091.md")
