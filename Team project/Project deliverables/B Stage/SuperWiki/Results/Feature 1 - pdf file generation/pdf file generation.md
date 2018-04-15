# Feature - pdf file generation

* Programmer: 
* Date: 

# Problem Definition

PDF file format is a standard file for various purposes including better readability from formatted output. SuperWiki uses markdown files for storing information, but users request the feature for converting the markdown files into pdf when they need to have the wiki contents in pdf form.
Converting markdown files to PDF files using pandoc can present users with a number of problems. 

The first problem is that pandoc installation requires an additional program called LaTeX to be installed. There are multiple programs that can be used for LaTeX, which can lead output to be different from user to user. 

A second major problem is that with pandoc’s command line arguments different users can specify different values for output parameters. (E.g. margins) Placing the PDF conversion feature beside the user’s markdown files allows each user to run the same conversion command, which leads to the same format in output. The use of the PDF conversion feature also removes the need for users to run command line commands.

## Requirement 

SuperWiki users should not install any tools such as pandoc to convert markdown files to PDF files.
SuperWiki users can generate PDF files from their markdown automatically. 
SuperWiki users have the same PDF output.

## User stories

1. As a user, I want to convert my wiki content in markdown file into a PDF file so that I can easily store my wiki content in a PDF file without additional tools installation.
2. As a user, I want to store my wiki content in the same PDF format so that I can have a single PDF format. 

# Design

## User story 1 

### Intefrace

* API name: We define `topdf` RESTFul API. 
* Input: the parameter is the name of the wiki. 
* Action: the pdf file is generated in the `Content` directory
* Output: the web-browser displays the generated pdf file.

### Detailed design

* To trigger PDF Converter, users use the API `topdf`

* The `@bp.route('/topdf/<path:url>/', methods=['GET', 'POST'])` decorator should be added to the `routes.py` file to call the `topdf` function.

* In the `topdf` function, `pypandoc.convert` function will be used from the "pypandoc" library.

* The markdown file is then selected from the content folder in the directory. The name of the markdown file is used to select the file from the content folder in the `pypandoc.convert` command such as `'content/' + filename + ' .md'`. The PDF output location is set to a separate folder named as PDF as `'PDF/' + filename + ' .pdf'`. 

* After generating the PDF file, the `topdf` function also checks to validate that the require file is created.

* Once the PDF file is created and validated, it is downloaded using the `send_file` command. The downloaded PDF file is displayed on the web browser. 

## User story 2

### Intefrace

User story 2 does not require interface as it only requires to generate the same format ouptut. In the test section, we verify that the two generated pdf files have the same format. 

### Detailed design

* The margins for the PDF file are then set using the `extra_args` element of the `pypandoc.convert` command: `extra_args=['-V', 'geometry:margin=1.5cm']`.

# Implementation


The dependencies used in this feature are:

* `pypandoc` - to convert the file to PDF version.
* `os` - to check if the PDF file has been created.

## User story 1

The Python code for implementation can be found at [Github location](...).

## User story 2

The Python code for implementation can be found at [Github location](...).

# Tests

* This test uses `pypandoc' to generate pdf files from markdown input. 
* The tests for the added features are included in attached `tests.py`. This test is can be found from [our project repository](...)
* In this test case, a test file is converted from `.md` to `.pdf` using `pypandoc.convert` command. Then the system checks if the file is correctly converted. If the file is converted, the console will print `"Feature One true"`.

## Checking the requirements

* User story1 - Test results (test1.pdf and test2.pdf) shows the automatic conversion from markdwon to pdf. 
* User story2 - Test outputs (test1.pdf and test2.pdf) shows the same format to satisfy the user story 2. 

## Limitations of tests

1. This test checks only if the pdf file is generated. We need to check the output `manually` to verfify that the two generated files have the same format (with 1.5cm margin)
2. We don't have unit test for this feature as we think this tests.py is enough to check the user stories are satisfied.

# User documentation

Users can use the API `topdf' with parameters the name of the wiki page to generate the pdf file and show the pdf file on the browser. 
