export class Ordering {
  orderingId: number;
  code: string;
  customerId: number;
  isParent: number;
  parentOrderingId: number;
  orderingDate: Date;
  orderingType: number;
  currency: number;
  priceBeforeTax: number;
  tax: number;
  priceAfterTax: number;
  totalAmount: number;
}
