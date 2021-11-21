import email
import quopri
from email.header import decode_header
import sys

s = """Received: by 10.52.251.227; Sat, 15 Feb 2014 10:00:00 -0800 (PST)
Date: Sat, 15 Feb 2014 10:00:00 -0800
Message-ID: <CAACdPr2WYKbzc70_X2fyBraX+PnBsPDpcifyOFCuAS7ZV=3e1Q@mail.gmail.com>
Subject: =?KOI8-R?B?9NLJINPP18XUwSDQzyDJ09DPzNjaz9fBzsnAIA==?=
	=?KOI8-R?B?R21haWw=?=
From: =?KOI8-R?B?68/Nwc7EwSBHbWFpbA==?= <mail-noreply@google.com>
To: =?KOI8-R?B?88HbwSDsxdfBzsTSz9fTy8nK?= <sashalev200149@gmail.com>
Content-Type: multipart/alternative; boundary=bcaec547cb6df548e604f275b0c2

--bcaec547cb6df548e604f275b0c2
Content-Type: text/plain; charset=KOI8-R
Content-Transfer-Encoding: base64

IPTSySDTz9fF1MEg0M8gydPQz8zY2s/Xwc7JwCBHbWFpbA0KW2ltYWdlOiBHb29nbGVdDQoNCvrE
0sHX09TX1crUxSwg88HbwSENCg0KDQrzz9fF1Nkg0M8gydPQz8zY2s/Xwc7JwCBHbWFpbA0KDQpb
aW1hZ2U6IOvPztTBy9TZXQ0K8MXSxc7F08nUxSDLz87UwcvU2SDJINPUwdLZxSDQydPYzcEg1yBH
bWFpbA0KDQrkzNEgwsXaws/MxdrOxc7Oz8fPINDF0sXIz8TBIM7BIEdtYWlsINfZIM3P1sXUxSDQ
xdLFzsXT1Mkg1yDOz9fZyiDR3cnLDQrLz87UwcvU2SDJINDJ09jNwSDJ2iDT1MHSz8fPINDP3tTP
18/HzyDLzMnFztTBLg0K8M/E0s/CzsXFLi4uPGh0dHBzOi8vc3VwcG9ydC5nb29nbGUuY29tL21h
aWwvYW5zd2VyLzE2NDY0MD9obD1ydSZyZWZfdG9waWM9MTY2OTAxND4NCltpbWFnZTog8M/J08td
DQri2dPU0tnKINDPydPLDQoNCu3P3c7ZyiDJztPU0tXNxc7UIEdvb2dsZSDwz8nTy8Eg0yDQz8TT
y8Hay8HNySDV3snU2dfBxdQg08/ExdLWwc7JxSDXwdvJyA0K0MnTxc0sINDSz9vM2cUg2sHQ0s/T
2SDJIMvPztTBy9TZIMkg0M/a18/M0cXUIMLZ09TSzyDOwcrUySDO1dbO2cUg08/Pwt3FzsnRLg0K
W2ltYWdlOiDwz8nTy10NCuLPzNjbxSwg3sXNINDSz9PUzyDQz97UwQ0KDQr3IEdtYWlsIM3P1s7P
IM/C3cHU2NPRINcg3sHUxSDJINXT1NLBydfB1Ngg18nExc/X09TSxd7JINDSySDQz83P3ckNCkhh
bmdvdXRzPGh0dHBzOi8vd3d3Lmdvb2dsZS5jb20vaW50bC9ydS9oYW5nb3V0cy8+Lg0K/tTPwtkg
0M/M2NrP18HU2NPRINzUyc0g08XS18nTz80gzsEgzc/CyczYzs/NINXT1NLPytPU18UsINPLwd7B
ytTFDQrQ0snMz9bFzsnFIEhhbmdvdXRzIMTM0Q0KQW5kcm9pZDxodHRwczovL3BsYXkuZ29vZ2xl
LmNvbS9zdG9yZS9hcHBzL2RldGFpbHM/aWQ9Y29tLmdvb2dsZS5hbmRyb2lkLnRhbGsmaGw9cnU+
yczJDQpBcHBsZSA8aHR0cHM6Ly9pdHVuZXMuYXBwbGUuY29tL3J1L2FwcC9oYW5nb3V0cy9pZDY0
MzQ5Njg2OD9tdD04Pi4NCg0KDQpbaW1hZ2U6IPrOwd7PyyBHbWFpbF3w0snR1M7Px88gz8Ldxc7J
0SENCuvPzcHOxMEgR21haWwNCiAoYykgMjAxMyBHb29nbGUgSW5jLiAxNjAwIEFtcGhpdGhlYXRy
ZSBQYXJrd2F5LCBNb3VudGFpbiBWaWV3LCBDQSA5NDA0Mw0K
--bcaec547cb6df548e604f275b0c2
Content-Type: text/html; charset=KOI8-R
Content-Transfer-Encoding: quoted-printable

<!DOCTYPE html>
<html><head><meta http-equiv=3D"content-type" content=3D"text/html;charset=
=3DUTF-8" /><title>=F4=D2=C9 =D3=CF=D7=C5=D4=C1 =D0=CF =C9=D3=D0=CF=CC=D8=
=DA=CF=D7=C1=CE=C9=C0 Gmail</title></head><body style=3D"background-color:#=
e5e5e5; margin:20px 0;"><br /><div style=3D"margin:2%;"><div style=3D"direc=
tion:ltr; text-align:left; font-family:'Open sans','Arial', sans-serif; col=
or:#444; background-color:white; padding:1.5em; border-radius:1em; box-shad=
ow:1px -5px 8px 2px #bbb; max-width:580px; margin:2% auto 0 auto;"><table s=
tyle=3D"background:white;width:100%"><tr><td><div style=3D"width:90px; heig=
ht:54px; margin:10px auto;"><img src=3D"https://services.google.com/fh/file=
s/emails/google_logo_flat_90_color.png" alt=3D"Google" width=3D"90" height=
=3D"34"/></div><div style=3D"width:90%; padding-bottom:10px; padding-left:1=
5px"><p><img alt=3D"" src=3D"https://ssl.gstatic.com/accounts/services/mail=
/msa/gmail_icon_small.png" style=3D"display:block; float:left; margin-top:4=
px; margin-right:5px;"/><span style=3D"font-weight:bold; font-size:small; l=
ine-height:1.4em">=FA=C4=D2=C1=D7=D3=D4=D7=D5=CA=D4=C5,  =F3=C1=DB=C1!</spa=
n></p><p><span style=3D"font-size:2.08em;"><br/>=F3=CF=D7=C5=D4=D9 =D0=CF =
=C9=D3=D0=CF=CC=D8=DA=CF=D7=C1=CE=C9=C0 Gmail</span><br/></p></div><p></p><=
div style=3D"float:left; clear:both; padding:0px 5px 10px 10px; "><img src=
=3D"https://services.google.com/fh/files/emails/importcontacts.png" alt=3D"=
=EB=CF=CE=D4=C1=CB=D4=D9" style=3D"display:block;"width=3D"129"height=3D"12=
9"/></div><div style=3D"float:left; vertical-align:middle; padding:10px; ma=
x-width:400px; float:left;"><table style=3D"vertical-align:middle;"><tr><td=
 style=3D"font-family:'Open sans','Arial',sans-serif;"><span style=3D"font-=
size:20px;">=F0=C5=D2=C5=CE=C5=D3=C9=D4=C5 =CB=CF=CE=D4=C1=CB=D4=D9 =C9 =D3=
=D4=C1=D2=D9=C5 =D0=C9=D3=D8=CD=C1 =D7 Gmail</span><br/><br/><span style=3D=
"font-size:small; line-height:1.4em">=E4=CC=D1 =C2=C5=DA=C2=CF=CC=C5=DA=CE=
=C5=CE=CE=CF=C7=CF =D0=C5=D2=C5=C8=CF=C4=C1 =CE=C1 Gmail =D7=D9 =CD=CF=D6=
=C5=D4=C5 =D0=C5=D2=C5=CE=C5=D3=D4=C9 =D7 =CE=CF=D7=D9=CA =D1=DD=C9=CB =CB=
=CF=CE=D4=C1=CB=D4=D9 =C9 =D0=C9=D3=D8=CD=C1 =C9=DA =D3=D4=C1=D2=CF=C7=CF =
=D0=CF=DE=D4=CF=D7=CF=C7=CF =CB=CC=C9=C5=CE=D4=C1. <a href=3D"https://suppo=
rt.google.com/mail/answer/164640?hl=3Dru&amp;ref_topic=3D1669014" style=3D"=
text-decoration:none; color:#15C">=F0=CF=C4=D2=CF=C2=CE=C5=C5&hellip;</a></=
span></td></tr></table></div><div style=3D"float:left; clear:both; padding:=
0px 5px 10px 10px; "><img src=3D"https://ssl.gstatic.com/mail/welcome/local=
ized/ru/welcome_search.png" alt=3D"=F0=CF=C9=D3=CB" style=3D"display:block;=
"width=3D"129"height=3D"129"/></div><div style=3D"float:left; vertical-alig=
n:middle; padding:10px; max-width:400px; float:left;"><table style=3D"verti=
cal-align:middle;"><tr><td style=3D"font-family:'Open sans','Arial',sans-se=
rif;"><span style=3D"font-size:20px;">=E2=D9=D3=D4=D2=D9=CA =D0=CF=C9=D3=CB=
</span><br/><br/><span style=3D"font-size:small; line-height:1.4em">=ED=CF=
=DD=CE=D9=CA =C9=CE=D3=D4=D2=D5=CD=C5=CE=D4 Google =F0=CF=C9=D3=CB=C1 =D3 =
=D0=CF=C4=D3=CB=C1=DA=CB=C1=CD=C9 =D5=DE=C9=D4=D9=D7=C1=C5=D4 =D3=CF=C4=C5=
=D2=D6=C1=CE=C9=C5 =D7=C1=DB=C9=C8 =D0=C9=D3=C5=CD, =D0=D2=CF=DB=CC=D9=C5 =
=DA=C1=D0=D2=CF=D3=D9 =C9 =CB=CF=CE=D4=C1=CB=D4=D9 =C9 =D0=CF=DA=D7=CF=CC=
=D1=C5=D4 =C2=D9=D3=D4=D2=CF =CE=C1=CA=D4=C9 =CE=D5=D6=CE=D9=C5 =D3=CF=CF=
=C2=DD=C5=CE=C9=D1.</span></td></tr></table></div><div style=3D"float:left;=
 clear:both; padding:0px 5px 10px 10px; "><img src=3D"https://ssl.gstatic.c=
om/accounts/services/mail/msa/welcome_hangouts.png" alt=3D"=F0=CF=C9=D3=CB"=
 style=3D"display:block;"width=3D"129"height=3D"129"/></div><div style=3D"f=
loat:left; vertical-align:middle; padding:10px; max-width:400px; float:left=
;"><table style=3D"vertical-align:middle;"><tr><td style=3D"font-family:'Op=
en sans','Arial',sans-serif;"><span style=3D"font-size:20px;">=E2=CF=CC=D8=
=DB=C5, =DE=C5=CD =D0=D2=CF=D3=D4=CF =D0=CF=DE=D4=C1</span><br/><br/><span =
style=3D"font-size:small; line-height:1.4em">=F7 Gmail =CD=CF=D6=CE=CF =CF=
=C2=DD=C1=D4=D8=D3=D1 =D7 =DE=C1=D4=C5 =C9 =D5=D3=D4=D2=C1=C9=D7=C1=D4=D8 =
=D7=C9=C4=C5=CF=D7=D3=D4=D2=C5=DE=C9 =D0=D2=C9 =D0=CF=CD=CF=DD=C9 <a href=
=3D"https://www.google.com/intl/ru/hangouts/" style=3D"text-decoration:none=
; color:#15C">Hangouts</a>. =FE=D4=CF=C2=D9 =D0=CF=CC=D8=DA=CF=D7=C1=D4=D8=
=D3=D1 =DC=D4=C9=CD =D3=C5=D2=D7=C9=D3=CF=CD =CE=C1 =CD=CF=C2=C9=CC=D8=CE=
=CF=CD =D5=D3=D4=D2=CF=CA=D3=D4=D7=C5, =D3=CB=C1=DE=C1=CA=D4=C5 =D0=D2=C9=
=CC=CF=D6=C5=CE=C9=C5 Hangouts =C4=CC=D1 <a href=3D"https://play.google.com=
/store/apps/details?id=3Dcom.google.android.talk&amp;hl=3Dru" style=3D"text=
-decoration:none; color:#15C">Android</a> =C9=CC=C9 <a href=3D"https://itun=
es.apple.com/ru/app/hangouts/id643496868?mt=3D8" style=3D"text-decoration:n=
one; color:#15C">Apple</a>.</span></td></tr></table></div><br/><br/>
<div style=3D"clear:both; padding-left:13px; height:6.8em;"><table style=3D=
"width:100%; border-collapse:collapse; border:0"><tr><td style=3D"width:68p=
x"><img alt=3D'=FA=CE=C1=DE=CF=CB Gmail' width=3D"49" height=3D"37" src=3D"=
http://ssl.gstatic.com/accounts/services/mail/msa/gmail_icon_large.png" sty=
le=3D"display:block;"/></td><td style=3D"align:left; font-family:'Open sans=
','Arial', sans-serif; vertical-align:bottom"><span style=3D"font-size:smal=
l">=F0=D2=C9=D1=D4=CE=CF=C7=CF =CF=C2=DD=C5=CE=C9=D1!<br/></span><span styl=
e=3D"font-size:x-large; line-height:1">=EB=CF=CD=C1=CE=C4=C1 Gmail</span></=
td></tr></table></div>
</td></tr></table></div>
<div style=3D"direction:ltr;color:#777; font-size:0.8em; border-radius:1em;=
 padding:1em; margin:0 auto 4% auto; font-family:'Arial', 'Helvetica', sans=
-serif; text-align:center;">&copy; 2013 Google Inc. 1600 Amphitheatre Parkw=
ay, Mountain View, CA 94043<br/></div></div></body></html>"""

s = sys.argv[1]
print(s)
print(quopri.decodestring(s, header=False).decode('KOI8-R'))

b = email.message_from_string(s)
if b.is_multipart():
    for payload in b.get_payload():
        # if payload.is_multipart(): ...
        print(quopri.decodestring(payload.get_payload(), header=False).decode('KOI8-R'))
else:
    print(quopri.decodestring(b.get_payload(), header=False).decode('KOI8-R'))