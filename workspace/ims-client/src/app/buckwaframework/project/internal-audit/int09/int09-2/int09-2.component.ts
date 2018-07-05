import { Component, OnInit } from "@angular/core";
import { ActivatedRoute } from "@angular/router";

@Component({
  selector: "app-int09-2",
  templateUrl: "./int09-2.component.html",
  styleUrls: ["./int09-2.component.css"]
})
export class Int092Component implements OnInit {
  id: any;

  constructor(private route: ActivatedRoute) {}

  ngOnInit() {
    this.id = this.route.snapshot.queryParams["id"];
    console.log(`ID: ${this.id}`);
  }
}
