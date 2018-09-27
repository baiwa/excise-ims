import { Component, OnInit } from "@angular/core";

declare var $: any;
@Component({
  selector: "app-int01-3-2",
  templateUrl: "./int01-3-2.component.html",
  styleUrls: ["./int01-3-2.component.css"]
})
export class Int0132Component implements OnInit {
  private productList: any[];

  private selectedProduct: string = "สุรา";

  constructor() {}

  ngOnInit() {
  
  }
  onChange(newValue) {
    this.selectedProduct = newValue; // don't forget to update the model here
  }


}
