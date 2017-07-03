#!/usr/bin/python
# -*- coding: utf-8 -*-
import urllib
from bs4 import BeautifulSoup

file = open("results.txt","a")

with open("urls.txt","r") as f:
    for line in f:
        url = line.strip()
	html = urllib.urlopen(url).read()
	soup = BeautifulSoup(html)

	for node in soup.findAll('p'):
    		file.write((''.join(node.findAll(text=True)).rstrip()).encode('utf-8').strip())

	
