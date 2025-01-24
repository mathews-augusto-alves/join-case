# Aplicação Web Next.js

## 📋 Descrição
Aplicação web moderna construída com Next.js 14, TypeScript e TailwindCSS, focada em fornecer uma interface de usuário robusta e responsiva com sistema de autenticação integrado.

## 🚀 Tecnologias Utilizadas
- Next.js 14
- TypeScript
- TailwindCSS
- Redux Toolkit
- Radix UI
- JWT para autenticação

## 🛠️ Requisitos
- Node.js 18+
- npm ou yarn
- Docker

## 🔧 Instalação e Configuração

### Instalação Local
```bash
# Instalar dependências
npm install

# Executar em modo desenvolvimento
npm run dev

# Construir para produção
npm run build

# Executar em produção
npm start
```

### Usando Docker
```bash
# Construir a imagem
docker build -t nextjs-app .

# Executar o container
docker run -p 3000:3000 nextjs-app
```

## 🏗️ Estrutura do Projeto
```
project/
├── app/                # Rotas e páginas
├── components/         # Componentes reutilizáveis
├── lib/               # Utilitários e lógica de negócios
├── hooks/             # Custom React hooks
├── service/           # Serviços e integrações
└── public/            # Arquivos estáticos
```

## ⚙️ Variáveis de Ambiente
Crie um arquivo `.env.local` na raiz do projeto:
```
NEXT_PUBLIC_API_URL=sua_url_api
```

## 🔐 Autenticação
O sistema utiliza JWT para autenticação, gerenciado através do Redux Toolkit. O token é armazenado de forma segura e gerenciado globalmente na aplicação.

## 🎨 UI/UX
- Interface moderna com Radix UI
- Estilização com TailwindCSS
- Sistema de notificações toast
- Componentes acessíveis
- Animações suaves

## 📦 Scripts Disponíveis
- `npm run dev`: Inicia o ambiente de desenvolvimento
- `npm run build`: Gera build de produção
- `npm start`: Inicia o servidor de produção
- `npm run lint`: Executa verificação de código