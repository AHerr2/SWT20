descendants(X,V) :- findall(W, ancestor(X,W), V).
ancestor(X,Y) :- parent(X,Y).
ancestor(X,Y) :- parent(X,Z), ancestor(Z,Y).
isdescendant(Y,X) :- findall(W, ancestor(X,W), K), member(Y,K).
parent(ecthelion,denethor).
parent(adrahil,finduilas).
parent(thengel,theodwin).
parent(morwen, theodwyn).
parent(thengel,theoden).
parent(morwen, theoden).
parent(denethor,boromir).
parent(finduilas,boromir).
parent(denethor,faramir).
parent(finduilas,faramir).
parent(faramir,elboron).
parent(eowyn,elboron).
parent(elboron,barahir).
parent(theodwin,eomer).
parent(eomer,elfweiin).
parent(lothiriel,elfweiin).
parent(theoden,theodred).
parent(elfhild,theored).