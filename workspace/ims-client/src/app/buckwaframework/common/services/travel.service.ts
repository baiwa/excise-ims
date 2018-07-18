import { Injectable } from "@angular/core";
import { TravelCostDetail, Contract } from "../models";

@Injectable()
export class TravelService {
  private travelDetail: TravelCostDetail;
  private contract: Contract;

  constructor() {}

  setTravelDetail(travelDetail: TravelCostDetail): void {
    this.travelDetail = travelDetail;
  }

  getTravelDetail(): TravelCostDetail {
    return this.travelDetail;
  }

  setTravelContract(contract: Contract): void {
    this.contract = contract;
  }

  getTravelContract(): Contract {
    return this.contract;
  }
}
