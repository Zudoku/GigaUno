# Aihe: GigaUno

GigaUno on peli joka muistuttaa läheisesti Uno korttipeliä, mutta siihen on lisätty erilaisia sääntöjä ja kortteja, jota alkuperäisessä pelissä ole. Pelin käyttöliittymä tehdään Swingillä. Alla on kuvattu pelin kulkua. 

Pelin säännöt ovat suoraan uno korttipelistä: Pelaaja joka saavuttaa pisterajan ensimmäisenä voittaa. Pelaaja saa pisteitä muiden käsissä olevista korteista kun hän pelaa viimeisimmän korttinsa pöytään. Korttien arvot on määritelty alla. Erän alussa kaikki ottavat pakasta yhden kortin joka määrittää, kuka aloittaa pelin. Samalla pöytään laitetaan yksi aloituskortti. 

Kun pelaajalla on vain yksi kortti, "huutaa" hän UNO! jolloin muut pelaajat saavat varoituksen että olet voittamassa. Jos et muista huutaa UNO ja vuoro siirtyy muulle pelaajalle ja joku muu huomaa virheesi, joudut nostamaan 2 korttia. 

Eriä pelataan kunnes jokin pelaaja on voittaja. Kun pelaajalla on vuoro, hän saa laittaa pöytään kortin jossa on sama väri, numero tai kuva kuin pöydän kortissa. Samalla vuorolla on mahdollista pelata monta korttia. Jos et voi tai halua laittaa korttia, on mahdollista myös nostaa yksi kortti, jolloin vuoro voi siirtyä seuraavalle. Pelissä on myös erikoiskortteja jotka sekoittavat pelin kulkua.

## Käyttäjät:
pelaajat (monta samaan aikaan)

## Pelaajan toiminnot:

- Pelin aloitus
	- Pelin aloituksessa valitaan kuinka monta pelaajaa on pelaamassa ja muut mahdolliset pelin säännöt, esim aikaraja jos sellaisen haluaa
- Pelin pelaaminen vuorotellen: yksi pelaaja pelaa peliä kerrallaan ja oman vuoron päätteeksi päästää toisen pelaajan pelaamaan 

## Erän kulku

- Peli alkaa ja on pelaajan 1 vuoro.
- Pelaaja 1 näkee korttinsa ja pelikentällä olevat kortit
- Pelaaja voi laittaa pelikentälle sopivia kortteja tai nostaa pakasta lisää jos sopivaa korttia ei ole
- Pelaajalla on mahdollisuus huutaa UNO jos kädessä on vain 1 kortti tai huomata muun pelaajan UNO jos hän ei sitä ole ilmoittanut
- Pelaaja päättää vuoron
- Vuoro siirtyy seuraavalle ja ruutuun tulee varmistus että oikea pelaaja on pelaamassa
- Seuraava pelaaja klikkaa varmistusnappulaa ja on hänen vuoronsa
- Erä loppuu kun jollain pelaajalla ei enää ole kortteja tai jos jokin erityissääntö rikkoutuu(esim aika kuluu umpeen)

## Pelin alussa säädettävät muuttujat

- Aikarajoitus
- Voittopiste-raja
- Erikoiskortit (voidaan valita checkbox tyylisesti mitä kortteja halutaan peliin)
- Korttipakan suuruus
- Mahdollisesti lisää säädettäviä asioita

## Erikoiskortit

- Nosta 2 
- Nosta 4
- Suunnanvaihtokortti
- Hurrikaanikortti
- Ohituskortti
- Villikortti
- Villikortti + nosta 4 
- Mahdollisesti muita erikoiskortteja

## Korttien arvot: (alustava)

kortti | arvo pelin lopussa
-------------------- | ------
0 | 1
1 | 2
2 | 3
3 | 4
4 | 5
5 | 6
6 | 7
7 | 8
8 | 9
9 | 10
Nosta 2 | 11
Nosta 4 | 11
Suunnanvaihto | 11
HurrikaaniKortti | 20
Ohituskortti | 11
Villikortti | 11
Villikortti + nosta 4 | 15

## Luokkakaavio

![Pelin logiikan rakenne](luokkakuva.png "Pelin logiikan rakenne")



