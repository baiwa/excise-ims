import { Component, OnInit } from "@angular/core";
import { ActivatedRoute } from "@angular/router";
import { TravelCostHeader, Contract } from "../../../../common/models";
import { TravelCostDetail } from "app/buckwaframework/common/models/travelcostdetail";
import { AjaxService } from "../../../../common/services";
import { TravelService } from "../../../../common/services/travel.service";
import {
  DecimalFormat,
  ArabicNumberToText,
  TextDateTH,
  formatter
} from "../../../../common/helper";

declare var $;
@Component({
  selector: "app-int09-2",
  templateUrl: "./int09-2.component.html",
  styleUrls: ["./int09-2.component.css"]
})
export class Int092Component implements OnInit {
  id: any;
  status: string;

  headerId: number;

  hdr: TravelCostHeader;
  detail: TravelCostDetail[];
  data: TravelCostDetail;

  typeDocs: string[];
  topics: string[][];
  topic: string[];
  total: string;

  selectDoc: string;
  selectTop: string;

  body: Contract;

  df: any;

  constructor(
    private route: ActivatedRoute,
    private ajax: AjaxService,
    private travelService: TravelService
  ) {
    this.df = new DecimalFormat("#,###.00");
  }

  ngOnInit() {
    $("#calendar").calendar({
      maxDate: new Date(),
      type: "date",
      text: TextDateTH,
      formatter: formatter()
    });
    // get data from service
    this.body = this.travelService.getTravelContract();
    this.body.sumCostTxt = this.ArabicNumberToText(this.body.sumCost);
    this.id = this.route.snapshot.queryParams["id"];
    if (this.id !== undefined) {
      const HEADER_URL = `ia/int09/lists/${this.id}`;
      this.ajax.get(HEADER_URL, res => {
        this.hdr = res.json()[0];
      });
    }
  }

  contractPdf(event) {
    event.preventDefault();
    const url = "report/pdf/contract/try"; //contract
    const body = this.body;
    this.ajax.post(url, body, res => {
      if (res.status == 200 && res.statusText == "OK") {
        // var byteArray = new Uint8Array(res.json());
        // var blob = new Blob([byteArray], { type: "application/pdf" });
        // if (window.navigator && window.navigator.msSaveOrOpenBlob) {
        //   window.navigator.msSaveOrOpenBlob(blob);
        // } else {
        //   var objectUrl = URL.createObjectURL(blob);
        //   window.open(objectUrl);
        // }
        window.open("/ims-webapp/api/report/pdf/Contract/file");
      }
    });
  }

  ArabicNumberToText(num) {
    return ArabicNumberToText(num);
  }

  toCommaAndDecimal(num) {
    return this.df.format(num.toString());
  }
}
