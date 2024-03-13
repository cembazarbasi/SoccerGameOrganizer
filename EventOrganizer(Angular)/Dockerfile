FROM node:18.17.1-alpine AS build
WORKDIR /app
COPY package*.json ./
RUN npm install
RUN npx ngcc --properties es 2023 browser module main -- first only --create-ivy-entry-points
COPY . .
RUN npm run build


FROM nginx:alpine
COPY --from=build /app/dist/angulareventplanner/ /usr/share/nginx/html

EXPOSE 80
