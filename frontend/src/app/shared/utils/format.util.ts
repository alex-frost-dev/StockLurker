export function formatCurrency(value: number, digitsInfo: string = '1.2-2'): string {
  const [minInt, rest] = digitsInfo.split('.');
  const [minFraction, maxFraction] = rest ? rest.split('-') : [2, 2];

  return new Intl.NumberFormat('es-ES', {
    style: 'currency',
    currency: 'EUR',
    minimumIntegerDigits: Number(minInt) || 1,
    minimumFractionDigits: Number(minFraction) || 2,
    maximumFractionDigits: Number(maxFraction) || 2,
  }).format(value);
}