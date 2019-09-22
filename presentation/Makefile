all: main

main:
	latexmk -pdf presentation.tex; latexmk -c

clean:
	latexmk -c
	rm *.bbl
	rm *.nav
	rm *.run.xml
	rm *.snm
	rm *.synctex.gz
	rm *.vrb
