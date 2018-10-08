# online_seva

This is project has started for online-seva.com site
it basically contain government job information.

Ui designed in angular js

Used Jasypt spring boot starter for encrypting password
$ ./encrypt.sh input="This is my message to be encrypted" password=MASTER_PASSWORD //for encrypt
$ ./decrypt.sh input=<encrypted password> password=MASTER_PASSWORD //for decrypt

Env Variable for master password
jasypt.encryptor.password=<Master password> OR
jasyptt_encryptor_password=<Master password> OR
JASYPT_ENCRYPTOR_PASSWORD=<Master password>