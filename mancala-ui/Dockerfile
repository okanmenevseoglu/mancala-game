# Stage 1 - Building the project
FROM node:16-alpine as build-step

RUN mkdir -p /app

WORKDIR /app

COPY . /app

RUN npm install

RUN npm run build --prod

# Stage 2 - Running the project
FROM nginx:1.19.10-alpine

COPY --from=build-step /app/dist/mancala-ui /usr/share/nginx/html

EXPOSE 80
