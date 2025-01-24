import * as React from 'react';

export interface DivProps
    extends React.HTMLAttributes<HTMLDivElement> { }

const Loader = React.forwardRef<HTMLDivElement, DivProps>(
    ({ className, ...props }, ref) => {
        return (
            <div className={className}>
                <div className="animate-spin rounded-full h-8 w-8 border-b-2 border-primary"></div>
            </div>
        );
    }
);
Loader.displayName = 'Loader';

export { Loader };