# Stage de dependências
FROM node:18-alpine AS deps
WORKDIR /app

# Copiar arquivos de dependências e o arquivo .env
COPY production.env production.env
COPY package.json package-lock.json ./

# Instalar dependências
RUN npm ci

# Stage de build
FROM node:18-alpine AS builder
WORKDIR /app

# Copiar dependências do stage anterior
COPY --from=deps /app/node_modules ./node_modules
COPY . .

# Copiar o arquivo .env para o container
COPY production.env production.env

# Adicionar argumento para o build
ARG CODESPACE_NAME
ARG NEXT_PUBLIC_API_URL
ENV NEXT_PUBLIC_API_URL=https://${CODESPACE_NAME}-8085.app.github.dev
ENV NEXT_TELEMETRY_DISABLED 1
RUN npm run build

# Stage de produção
FROM node:18-alpine AS runner
WORKDIR /app

ENV NODE_ENV production
ENV NEXT_TELEMETRY_DISABLED 1

# Adicionar usuário não-root
RUN addgroup --system --gid 1001 nodejs
RUN adduser --system --uid 1001 nextjs

# Copiar arquivos necessários
COPY --from=builder /app/public ./public
COPY --from=builder --chown=nextjs:nodejs /app/.next/standalone ./
COPY --from=builder --chown=nextjs:nodejs /app/.next/static ./.next/static

# Mudar para usuário não-root
USER nextjs

# Expor porta
EXPOSE 3000

# Adicionar novamente a variável de ambiente para produção
ENV PORT 3000
ENV HOSTNAME "0.0.0.0"
ARG NEXT_PUBLIC_API_URL
ARG CODESPACE_NAME
ENV NEXT_PUBLIC_API_URL=https://${CODESPACE_NAME}-8085.app.github.dev

# Comando para executar a aplicação
CMD ["node", "server.js"]
