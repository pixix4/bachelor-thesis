all: main

title: titlepage/title.tex
	cd titlepage; latexmk -pdf title.tex; latexmk -c

main: title
	latexmk -pdf main.tex; latexmk -c

clean:
	latexmk -c
	cd titlepage; latexmk -c
