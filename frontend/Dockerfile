FROM node:19.1.0-alpine AS builder
WORKDIR /usr/src/app
USER root
RUN adduser -h /home/nodeuser -u 1111 -D nodeuser
USER nodeuser
COPY --chown=nodeuser:nodeuser . ./
RUN yarn install && yarn build

FROM nginxinc/nginx-unprivileged:1.21.6-alpine
WORKDIR /usr/src/app/
USER root
RUN rm -rf /usr/share/nginx/html/* \
    && adduser -h /home/nodeuser -u 1111 -D nodeuser
COPY --chown=nodeuser:nodeuser --from=builder /usr/src/app/build /usr/share/nginx/html
USER nodeuser
CMD exec nginx -g "daemon off;"
