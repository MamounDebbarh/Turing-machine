states 26
A
Ad
B
Bd
C
Cd
Cc
D
E
Ec
F
G
Gc
H
Hc
Lc
sk1a
sk1b
sk2a
sk2b
sk2c
sk2d
sk3
sk4
reject -
accept +
alphabet 10 0 1 # p d f s n c l
A 0 sk1a p R
A p A p R
A f Ad f R
A # B l R
A s accept _ L
A n accept _ L
Ad d Ad d R
Ad s Bd s R
Bd n Bd n R
Bd c Lc c R
Lc 1 Lc 1 R
Lc _ accept _ L
Lc n Lc n R
Bd _ accept _ L
sk1a 0 sk1a 0 R
sk1a 1 sk1a 1 R
sk1a # B f R
sk1a f B f R
B 0 sk2a d R
B d B d R
B # accept _ L
B s accept _ L
B n accept _ L
sk2a 0 sk2a 0 R
sk2a 1 sk2a 1 R
sk2a # C s R
sk2a s C s R
sk2a _ accept _ L
C n C n R
C c Cc c R
Cc n C n R
Cc c Cc c R
Cc 0 reject _ L
Cc 1 D n L
C 1 reject _ L
C 0 D n L
C # accept _ L
C s accept _ L
C _ accept _ L
B 1 sk2b d R
sk2b 0 sk2b 0 R
sk2b 1 sk2b 1 R
sk2b # E s R
sk2b s E s R
sk2b _ reject _ L
E n E n R
E c Ec c R
Ec c Ec c R
Ec n E n R
Ec 1 reject _ L
Ec 0 D c L
E 0 reject _ L
E 1 D n L
D 0 D 0 L
D 1 D 1 L
D # D # L
D n D n L
D c D c L
D d D d L
D f D f L
D s D s L
D p A p R
D l B l R
A 1 sk1b p R
sk1b 0 sk1b 0 R
sk1b 1 sk1b 1 R
sk1b # F f R
sk1b f F f R
F 0 sk2c d R
F d F d R
F # accept _ L
F s accept _ L
F n accept _ L
sk2c 0 sk2c 0 R
sk2c 1 sk2c 1 R
sk2c # G s R
sk2c s G s R
G n G n R
Gc n G n R
G c Gc c R
Gc c Gc c R
Gc 1 reject _ L
Gc 0 D c L
G 0 reject _ L
G 1 D n L
G # accept _ L
G s accept _ L
G _ accept _ L
F 1 sk2d d R
sk2d 0 sk2d 0 R
sk2d 1 sk2d 1 R
sk2d # H s R
sk2d s H s R
H n H n R
Hc n H n R
H c Hc c R
Hc c Hc c R
Hc 0 reject _ L
Hc 1 D c L
H 1 reject _ L
H 0 D c L
