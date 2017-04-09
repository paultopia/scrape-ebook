Some people decide, annoyingly, to publish books online via open access licenses, but as a bunch of individual chapter-by-chapter pdf files.

Example: O'Reilly makes [Managing Projects with GNU Make, by Robert Mecklenburg](http://www.oreilly.com/openbook/make3/book/index.html) available under a creative commons license, but in a bunch of individual PDF files.

If you want the whole book, you have to download them individually by hand, and then merge them by hand.  Well, no more. 

Instead, download a release of this, stick it on your $PATH, have a recent version of java to run it, and then just do 

`bookscraper http://www.oreilly.com/openbook/make3/book/index.html gnu-make-book.pdf` 

and the entire book will appear, as if by magic.

Notes: 

1.  Be patient.  It will take a few seconds to fetch all the PDFs and merge them.

2.  You might get a bunch of warnings about not closing pdf files.  Ignore them. 

This works in the simplest possible way: it loads the page you give it, extracts every link that ends in ".pdf", in order, saves all those pdf files in a temp directory, merges them using pdfbox, and then deletes the temp file. It's less than 50 lines of code, including whitespace.
