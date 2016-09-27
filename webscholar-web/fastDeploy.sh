echo "removendo antigo deploy"
rm webscholar-web.war
echo "enviando novo"
mv /usr/app/webscholar-web.war /usr/glassfish4
echo "mandando implantar"
asadmin redeploy --name webscholar-web webscholar-web.war