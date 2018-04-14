# Feature tests.

# Feature PDF Coverter
import pypandoc
import os

# Feature One - PDF Converter
# Convert the sample markdown file to a PDF file.
pypandoc.convert('content_test/test.md', 'pdf', outputfile="PDF_test/test.pdf", extra_args=['-V', 'geometry:margin=1.5cm'])
pypandoc.convert('content_test/test2.md', 'pdf', outputfile="PDF_test/test2.pdf", extra_args=['-V', 'geometry:margin=1.5cm'])

# Store the location where the converted file should be stored.
filename = "PDF_test/test.pdf"

# Check if the file was converted.
if os.path.exists(filename):

    # If the file was converted display a message.
    print("Feature One True")
else:

    # If the file was not converted display a message.
    print("Feature One False")
