# Electronic-Gradebook

Aplicatia prezinta un catalog electronic in care fiecare student face parte dintr-o grupa, 
are atribuite note aferente cursurilor la care participa, fiecare asistent si profesor valideaza
notele elevilor sai, si fiecare parinte poate vedea notele copilului.

Aplicatia le permite parintilor unui student sa se aboneze la Catalog pentru a putea
primi notificari in momentul in care copilul este notat de catre un profesor sau de catre un
asistent. Pentru a putea realiza acest lucru, am folosit sablonul de proiectare Observer.

Fiecare profesor va aplica o politica prin care la sfarsitul semestrului selecteaza cel mai bun
student. Pentru a realiza acest lucru in cadrul implementarii, am folosit sablonul de
proiectare Strategy.

Folosind sablonul de proiectare Visitor, am implementat functionalitatea prin care fiecare
asistent o sa poata completa notele de pe parcurs ale studentilor, iar fiecare profesor o sa
poata completa notele de la examen ale studentilor sai. In metodele din clasa ScoreVisitor a fost 
apelata si metoda de trimitere a notificarilor catre parinti.

Pentru un curs exista posibilitatea de a face un back-up al notelor, iar pentru acest
lucru am folosit sablonul de proiectare Memento.

Am realizat si o interfata grafica minimalista care permite autentificarea in functie de 
tipul utilizatorului (student, asistent, profesor sau parinte) si afiseaza/executa actiunile 
corespunzatoare.

Exista 2 clase folosite pentru testare, Test si Test2.
Clasa Test parseaza un fisier de intrare, input.txt, de unde extrage datele si le pune
in catalog. Urmeaza apelarea interfatei grafice care realizeaza intreaga operatiune de
trecere a notelor: validare, primire si notificare. Clasa Test2 este folosita doar pentru
a demonstra functionalitatea acelor functii pe care nu le am folosit in programul
principal si au fost impuse.
