FROM ubuntu
RUN apt update -y
RUN apt install apache2 -y
COPY ./Chinnuflix /var/www/html
CMD ["/usr/sbin/apachectl", "-D", "FOREGROUND"]