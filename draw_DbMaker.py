#!/usr/bin/env python
#-*-coding:utf-8-*-

# http://www.aydinsari.com.tr/10subat2018.php
# Sitesinde Bulunan Soruları, Cevap Seçeneklerini 
# Ve Doğru Vevapları Çekerek Bir VeriTabanına Kaydeden Python Betiği

import sqlite3 as sql
import urllib3 as url
import requests
from bs4 import BeautifulSoup
import re
import sys
import time
import urllib

def ResimKaydet(Resimler):
	DosyaAdi = []
	for Resim in Resimler:
		SiteURL = "http://www.aydinsari.com.tr/"
		img = str(Resim)[str(Resim).find("src=\"")+5 : str(Resim).find("\"",str(Resim).find("src=\"")+5)]
		if img.find("http://www.aydinsari.com.tr/") == -1 and img.find("https://www.aydinsari.com.tr/") == -1:
			img = SiteURL + img
		print "imglink : " + img
		DosyaAdi1 = img[img.rfind("/")+1:]
		print('indiriliyor ' + DosyaAdi1 + '...')
		urllib.urlretrieve(img, DosyaAdi1) 
		DosyaAdi.append(DosyaAdi1)
	return DosyaAdi

reload(sys)
sys.setdefaultencoding('utf-8')

tag_re = re.compile(r'(<!--.*?-->|<[^>]*>)')

#Bağlan
db = sql.connect('Sorular.db')
#Bağlantıyı Kontrol Et
if(db):
	print unicode('Veritabanı Bağlantısı Başarı İle Kuruldu.',"utf-8")
else:
	print unicode('Veritabanı Bağlantısı Başarısız.',"utf-8")
	
dbc = db.cursor()

#dbc.execute('''CREATE TABLE SORULAR(id INTEGER PRIMARY KEY,Soru VARCHAR(1000),C1 VARCHAR(50),C2 VARCHAR(50),C3 VARCHAR(50),C4 VARCHAR(50),DC VARCHAR(20), Sinav VARCHAR(50))''')

response = requests.post(sys.argv[1],data={"cevapla":"+Sonuçlara+Ulaşmak+İçin+Tıklayın+"})
print '[+] Sunucunun Cevabı : ' + str(response.status_code)

if response.status_code == 200:
	content = response.content
	print '[+] Sayfa İçeriği Cekildi.'
	print content
	soup = BeautifulSoup(content, "lxml")
	Sinav = soup.find_all("div", "baslikg")
	Sinav = unicode(tag_re.sub('', str(Sinav[0]).replace('Cevaplarınız','')),soup.original_encoding)
	sorular = soup.find_all("div", "soru")

	for soru in sorular:
		print '------------------------------------------------------------------------------'
		resimler = soru.find_all("img")
		ResimListesi = ResimKaydet(resimler)
		Soru = ""
		C1 = ""
		C2 = ""
		C3 = ""
		C4 = ""
		DC = ""
		try:
			for sayici, resim in enumerate(resimler):
				print " ++ ++ ++ ++ ++ ++ "
				print str(resim)
				print " ++ ++ ++ ++ ++ ++ "
				print str(soru)
				print " ++ ++ ++ ++ ++ ++ "
				soru = str(soru).replace(str(resim),"\n[image:" + ResimListesi[sayici] + "]\n")
				print str(soru)
				print " ++ ++ ++ ++ ++ ++ "
			Soru = unicode(tag_re.sub('', str(soru).replace('Bu soruyu Boş Bıraktınız.','')),soup.original_encoding)
			C1 = Soru[Soru.find("A)"):Soru.find("B)")]
			C2 = Soru[Soru.find("B)"):Soru.find("C)")]
			C3 = Soru[Soru.find("C)"):Soru.find("D)")]
			C4 = Soru[Soru.find("D)"):Soru.find("Doğru Cevap:")]
			DC = Soru[Soru.find("Doğru Cevap:"):]
			Soru = Soru.replace(C1,"").replace(C2,"").replace(C3,"").replace(C4,"").replace(DC,"")
			print '[+] Soru : ' + Soru
			print '[+] Cevap 1 : ' + C1
			print '[+] Cevap 2 : ' + C2
			print '[+] Cevap 3 : ' + C3
			print '[+] Cevap 4 : ' + C4
			print '[+] Doğru Cevap : ' + DC
			print '[+] Sinav : ' + Sinav
		except:
			print unicode('Soru Ekrana Yazdırılamadı',"utf-8")
		print '------------------------------------------------------------------------------'
		
		dbc.execute('''INSERT INTO SORULAR(Soru,C1,C2,C3,C4,DC,Sinav) VALUES (' '''+ Soru.replace('\'',' ') +''' ',' '''+ C1.replace('\'',' ') +''' ',' '''+ C2.replace('\'',' ') +''' ',' '''+ C3.replace('\'',' ') +''' ',' ''' + C4.replace('\'',' ') + ''' ',' ''' + DC.replace('\'',' ') + ''' ',' ''' + Sinav.replace('\'',' ') + ''' ')''')	
			
db.commit()
db.close()
