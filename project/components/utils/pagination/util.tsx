import { ItemPaginacao, PaginacaoEllipsis, PaginacaoLink } from "@/components/ui/pagination";

interface Props {
    currentPage: number;
    totalPages: number;
    handlePageChange: (param: number) => void;
}

export const renderItemPaginacaos = ({ currentPage, totalPages,handlePageChange }: Props) => {
    const items = [];
    const maxVisiblePages = 3;
    const halfVisible = Math.floor(maxVisiblePages / 2);

    let startPage = Math.max(1, currentPage - halfVisible);
    let endPage = Math.min(totalPages, startPage + maxVisiblePages - 1);

    if (endPage - startPage + 1 < maxVisiblePages) {
      startPage = Math.max(1, endPage - maxVisiblePages + 1);
    }

    if (startPage > 1) {
      items.push(
        <ItemPaginacao key="1">
          <PaginacaoLink onClick={() => handlePageChange(1)}>1</PaginacaoLink>
        </ItemPaginacao>
      );
      if (startPage > 2) {
        items.push(
          <ItemPaginacao key="start-ellipsis">
            <PaginacaoEllipsis />
          </ItemPaginacao>
        );
      }
    }

    for (let i = startPage; i <= endPage; i++) {
      items.push(
        <ItemPaginacao key={i}>
          <PaginacaoLink
            isActive={currentPage === i}
            onClick={() => handlePageChange(i)}
          >
            {i}
          </PaginacaoLink>
        </ItemPaginacao>
      );
    }

    if (endPage < totalPages) {
      if (endPage < totalPages - 1) {
        items.push(
          <ItemPaginacao key="end-ellipsis">
            <PaginacaoEllipsis />
          </ItemPaginacao>
        );
      }
      items.push(
        <ItemPaginacao key={totalPages}>
          <PaginacaoLink onClick={() => handlePageChange(totalPages)}>
            {totalPages}
          </PaginacaoLink>
        </ItemPaginacao>
      );
    }

    return items;
  };