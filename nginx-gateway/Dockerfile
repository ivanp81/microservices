FROM nginx:alpine

RUN rm -rf /etc/nginx/conf.d
COPY conf /etc/nginx

EXPOSE 8080

CMD ["nginx", "-g", "daemon off;"]