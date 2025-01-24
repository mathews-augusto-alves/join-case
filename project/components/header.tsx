'use client';

import React from 'react';
import { useSelector, useDispatch } from 'react-redux';
import Link from 'next/link';
import { ShoppingCart, User, LogOut } from 'lucide-react';
import { Button } from './ui/button';
import { RootState } from '@/lib/store/store';
import {
  DropdownMenu,
  DropdownMenuContent,
  DropdownMenuItem,
  DropdownMenuTrigger,
} from './ui/dropdown-menu';
import { logout } from '@/lib/store/authSlice';

export function Header() {
  const dispatch = useDispatch();
  const { items } = useSelector((state: RootState) => state.carrinho);
  const { isAuthenticated } = useSelector((state: RootState) => state.auth);

  const handleLogout = () => {
    dispatch(logout());
  };

  return (
    <header className="border-b">
      <div className="container mx-auto px-4 py-4 flex items-center justify-between">
        <Link href="/" className="text-2xl font-bold">
          E-commerce
        </Link>

        <nav className="flex items-center space-x-8">
          <Link href="/categorias" className="hover:text-primary">
            Categorias
          </Link>
          <Link href="/produtos" className="hover:text-primary">
            Produtos
          </Link>
        </nav>

        <div className="flex items-center space-x-4">
          <Link href="/carrinho">
            <Button variant="ghost" className="relative">
              <ShoppingCart className="h-5 w-5" />
              {items.length > 0 && (
                <span className="absolute -top-2 -right-2 bg-primary text-primary-foreground rounded-full w-5 h-5 text-xs flex items-center justify-center">
                  {items.length}
                </span>
              )}
            </Button>
          </Link>

          {isAuthenticated ? (
            <DropdownMenu>
              <DropdownMenuTrigger asChild>
                <Button variant="ghost">
                  <User className="h-5 w-5" />
                </Button>
              </DropdownMenuTrigger>
              <DropdownMenuContent align="end">
                <Link href="/conta">
                  <DropdownMenuItem>
                    <User className="mr-2 h-4 w-4" />
                    Minha Conta
                  </DropdownMenuItem>
                </Link>
                <DropdownMenuItem onClick={handleLogout}>
                  <LogOut className="mr-2 h-4 w-4" />
                  Sair
                </DropdownMenuItem>
              </DropdownMenuContent>
            </DropdownMenu>
          ) : (
            <div className="flex items-center space-x-2">
              <Link href="/login">
                <Button variant="ghost">Login</Button>
              </Link>
              <Link href="/registro">
                <Button>Registro</Button>
              </Link>
            </div>
          )}
        </div>
      </div>
    </header>
  );
}