FROM node:16.14.2-bullseye-slim AS builder
WORKDIR /usr/src/app
RUN groupadd --gid 1111 nodeuser \
  && useradd --home-dir /home/nodeuser --create-home --uid 1111 \
  --gid 1111 --shell /bin/sh --skel /dev/null nodeuser
COPY --chown=nodeuser:nodeuser . ./
RUN npm install && npm run build

FROM nginxinc/nginx-unprivileged:1.21.6-alpine
RUN groupadd --gid 1111 nodeuser \
  && useradd --home-dir /home/nodeuser --create-home --uid 1111 \
  --gid 1111 --shell /bin/sh --skel /dev/null nodeuser
COPY --chown=nodeuser:nodeuser --from=builder /usr/src/app/build /usr/share/nginx/html
EXPOSE 80
USER nodeuser
CMD ["npm", "start"]
CMD exec nginx -g "daemon off;"
