export class TaxReceipt {
    [x: string]: any;
    // String
    taxReceiptId: string;
    receiptDate: string;
    depositDate: string;
    sendDate: string;
    incomeName: string;
    receiptNo: string;
    // Number
    netTaxAmount: number;
    netLocAmount: number;
    locOthAmount: number;
    locExpAmount: number;
    olderFundAmount: number;
    tpbsFundAmount: number;
    sendAmount: number;
    stampAmount: number;
	customAmount: number;
}