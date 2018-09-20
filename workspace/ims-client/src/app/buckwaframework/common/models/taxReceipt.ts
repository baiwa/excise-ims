export class TaxReceipt {
    [x: string]: any;
    // String
    taxReceiptId: string = "-";
    receiptDate: string = "-";
    depositDate: string = "-";
    sendDate: string = "-";
    incomeName: string = "-";
    receiptNo: string = "-";
    // Number
    netTaxAmount: number = 0.0;
    netLocAmount: number = 0.0;
    locOthAmount: number = 0.0;
    locExpAmount: number = 0.0;
    olderFundAmount: number = 0.0;
    tpbsFundAmount: number = 0.0;
    sendAmount: number = 0.0;
    stampAmount: number = 0.0;
	customAmount: number = 0.0;
}