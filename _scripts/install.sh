#!/bin/bash
set -x # Show the output of the following commands (useful for debugging)

# Import the SSH deployment key
openssl aes-256-cbc -K $encrypted_b06319473f3e_key -iv $encrypted_b06319473f3e_iv -in id_rsa.enc -out id_rsa -d
eval "$(ssh-agent -s)"
#chmod 600 $TRAVIS_BUILD_DIR/id_rsa
#ssh-add $TRAVIS_BUILD_DIR/id_rsa

rm id_rsa.enc # Don't need it anymore
chmod 600 id_rsa
mv id_rsa ~/.ssh/id_rsa