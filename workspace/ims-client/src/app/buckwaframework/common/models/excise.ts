// Desired for semantic-ui Dropdown.
export class Excise {
    change: any
    coordinates: any
    createdBy: String
    createdDatetime: any
    exciseArea: String
    exciseFacAddress: any
    exciseFacName: String
    exciseId: String
    exciseIdenNumber: String
    exciseOperatorName: String
    exciseRegisCapital: number
    exciseRegisttionNumberId: number
    exciseRemark: String
    exciseTax: ExciseTax[]
    industrialAddress: String
    no1: any
    no2: any
    no3: any
    payingtax: any
    paymentMonth: any
    registeredCapital: any
    sector: any
    status: any
    taexciseProductType: String
    taexciseSectorArea: String
    taxpayment1: any
    taxpayment2: any
    updateBy: String
    updateDatetime: any
}

class ExciseTax {
    createdBy: any
    createdDatetime: any
    exciseId: String
    exciseTaxReceiveAmount: String
    exciseTaxReceiveId: number
    exciseTaxReceiveMonth: String
    updateBy: String
    updateDatetime: any
}