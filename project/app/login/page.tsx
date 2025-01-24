'use client';

import { useState } from 'react';
import { useDispatch } from 'react-redux';
import { useRouter } from 'next/navigation';
import { Button } from '@/components/ui/button';
import { Input } from '@/components/ui/input';
import { Label } from '@/components/ui/label';
import Link from 'next/link';
import usuarioLogin from '@/service/loginService';
import { useToast } from '@/hooks/toast/use-toast';
import { login } from '@/lib/store/authSlice';
import CustomException from '@/lib/exception/CustomException';
import { Loader2 } from 'lucide-react';

export default function LoginPage() {
  const { toast } = useToast();
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [loading, setLoading] = useState(false);
  const dispatch = useDispatch();
  const router = useRouter();

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    setLoading(true);
    try {
      const usuario = await usuarioLogin(email, password);
      dispatch(login(usuario));
      router.push('/produtos');
    } catch (ex) {
      const error = ex as CustomException;
      toast({
        title: error.title,
        description: error.errors
      });
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="max-w-md mx-auto">
      <h1 className="text-2xl font-bold text-center mb-6">Login</h1>

      <form onSubmit={handleSubmit} className="space-y-4">
        <div className="space-y-2">
          <Label htmlFor="email">Email</Label>
          <Input
            id="email"
            type="email"
            value={email}
            onChange={(e) => setEmail(e.target.value)}
            required
          />
        </div>

        <div className="space-y-2">
          <Label htmlFor="password">Senha</Label>
          <Input
            id="password"
            type="password"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
            required
          />
        </div>

        <Button type="submit" className="w-full" disabled={loading}>
          {loading ? <Loader2 className="animate-spin text-white-500"/> : 'Login' }
        </Button>
      </form>

      <p className="text-center mt-4">
        Não tem uma conta?{' '}
        <Link href="/registro" className="text-primary hover:underline">
          Registre-se
        </Link>
      </p>
    </div>
  );
}