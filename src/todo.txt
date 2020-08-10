states 7
st
s0
s1
t0
t1
td
accept +
alphabet 2 0 1
st _ accept _ L
st 0 s0 _ R
st 1 s1 _ R
s0 0 s0 0 R
s0 1 s0 1 R
s0 _ t0 _ L
s1 0 s1 0 R
s1 1 s1 1 R
s1 _ t1 _ L
t0 0 td _ L
t0 _ accept _ L
t1 1 td _ L
t1 _ accept _ L
td 0 td 0 L
td 1 td 1 L
td _ st _ R
