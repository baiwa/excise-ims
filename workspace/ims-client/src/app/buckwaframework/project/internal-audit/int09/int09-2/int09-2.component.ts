import { Component, OnInit } from "@angular/core";
import { ActivatedRoute } from "@angular/router";
import { TravelCostHeader } from "../../../../common/models";
import { TravelCostDetail } from "app/buckwaframework/common/models/travelcostdetail";
import { AjaxService } from "../../../../common/services";

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

  constructor(private route: ActivatedRoute, private ajax: AjaxService) {}

  ngOnInit() {
    this.id = this.route.snapshot.queryParams["id"];
    if (this.id !== undefined) {
      const HEADER_URL = `ia/int09/lists/${this.id}`;
      this.ajax.get(HEADER_URL, res => {
        this.hdr = res.json()[0];
      });
    }
  }
}
