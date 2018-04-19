# Feature - Unit testing of added content

* Programmer: Ronnie Hoover
* Date: 4/18/18

# Problem Definition

New content added the wiki required unit tests to ensure continued functionality. 

## Requirement

Test features added by the `ArchivePage` and `RestorePage` objects, as well as from the added function of `SearchHistory`.

Ensure that any testing does not leave pages within the database.

## User Stories

User story 1 - As a developer I would like to ensure that my teams code is properly being tested so that we have a better metric as to whether changes in the code cause any issues.

# Design

## User Story 1

### Interface

* Test Classes: test_core.WikiTestCase, test_web.TestArchivePage, and test_web.TestRestorePage
* Action: Assert cases for specific functions.

### Detailed Design

* Tests created are ran alongside prior standing tests.

### Implementation

* `test_store` stores a example markdown file to the database, then asserts that the content is accurate.
* `teat_restore` stores an example markdown file, stores an alternate version of the same file to emulate a new commit of the same page. Then attempts to restore the first commit and asserts the original content matches
* `test_restoreDeleted` stores an example markdown file, then restores it to a new file. The asserts that the file is present.
* `test_searchHistory` retrieves the current dictionary of changes, then asserts that the number of pages is equal to the number of distinct pages.