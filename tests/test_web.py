from tests import WikiBaseTestCase
from wiki.web.user import UserManager

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

TestFilePath = '/Users/Ryan/Riki/tests/'


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