from . import WikiBaseTestCase
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


TestFilePath = '/Users/liamtiemon/PycharmProjects/Riki/tests'


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
        m.add_user('ronnie', 'password', authentication_method='hash')
        self.assertTrue(m.delete_user('ronnie'))

    def test_invalid_authentication_method(self):
        m = UserManager(TestFilePath)
        self.assertRaises(NotImplementedError, m.add_user, m, 'Elizabeth', 12345, authentication_method='this will cause an error')