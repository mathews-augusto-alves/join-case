import * as React from 'react';
import { ChevronLeft, ChevronRight, MoreHorizontal } from 'lucide-react';

import { cn } from '@/lib/utils';
import { ButtonProps, buttonVariants } from '@/components/ui/button';

const Paginacao = ({ className, ...props }: React.ComponentProps<'nav'>) => (
  <nav
    role="navigation"
    aria-label="pagination"
    className={cn('mx-auto flex w-full justify-center', className)}
    {...props}
  />
);
Paginacao.displayName = 'Paginacao';

const ConteudoPaginacao = React.forwardRef<
  HTMLUListElement,
  React.ComponentProps<'ul'>
>(({ className, ...props }, ref) => (
  <ul
    ref={ref}
    className={cn('flex flex-row items-center gap-1', className)}
    {...props}
  />
));
ConteudoPaginacao.displayName = 'ConteudoPaginacao';

const ItemPaginacao = React.forwardRef<
  HTMLLIElement,
  React.ComponentProps<'li'>
>(({ className, ...props }, ref) => (
  <li ref={ref} className={cn('', className)} {...props} />
));
ItemPaginacao.displayName = 'ItemPaginacao';

type PaginacaoLinkProps = {
  isActive?: boolean;
} & Pick<ButtonProps, 'size'> &
  React.ComponentProps<'a'>;

// Botão usado por todas as opções
const PaginacaoLink = ({
  className,
  isActive,
  size = 'icon',
  ...props
}: PaginacaoLinkProps) => (
  <a
    aria-current={isActive ? 'page' : undefined}
    className={cn(
      buttonVariants({
        variant: isActive ? 'outline' : 'ghost',
        size,
      }),
      className,
      'cursor-pointer'
    )}
    {...props}
  />
);
PaginacaoLink.displayName = 'PaginacaoLink';

// Opção de voltar para a página anterior
const PaginacaoAnterior = ({
  className,
  ...props
}: React.ComponentProps<typeof PaginacaoLink>) => (
  <PaginacaoLink
    aria-label="Vá para página anterior"
    size="default"
    className={cn('gap-1 pl-2.5', className)}
    {...props}
  >
    <ChevronLeft className="h-4 w-4" />
    <span>Anterior</span>
  </PaginacaoLink>
);
PaginacaoAnterior.displayName = 'PaginacaoAnterior';

const ProximaPaginacao = ({
  className,
  ...props
}: React.ComponentProps<typeof PaginacaoLink>) => (
  <PaginacaoLink
    aria-label="Vá para a próxima página"
    size="default"
    className={cn('gap-1 pr-2.5', className)}
    {...props}
  >
    <span>Próxima</span>
    <ChevronRight className="h-4 w-4" />
  </PaginacaoLink>
);
ProximaPaginacao.displayName = 'ProximaPaginacao';

// Opção de páginas ocultas por padrão
const PaginacaoEllipsis = ({
  className,
  ...props
}: React.ComponentProps<'span'>) => (
  <span
    aria-hidden
    className={cn('flex h-9 w-9 items-center justify-center', className)}
    {...props}
  >
    <MoreHorizontal className="h-4 w-4" />
    <span className="sr-only">Mais páginas</span>
  </span>
);
PaginacaoEllipsis.displayName = 'PaginacaoEllipsis';

export {
  Paginacao,
  ConteudoPaginacao,
  PaginacaoEllipsis,
  ItemPaginacao,
  PaginacaoLink,
  ProximaPaginacao,
  PaginacaoAnterior,
};
