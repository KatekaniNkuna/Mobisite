import { Moment } from 'moment';
import { CompetitorName } from 'app/shared/model/enumerations/competitor-name.model';
import { Province } from 'app/shared/model/enumerations/province.model';
import { ProductType } from 'app/shared/model/enumerations/product-type.model';
import { TypeOfOffer } from 'app/shared/model/enumerations/type-of-offer.model';
import { OfferType } from 'app/shared/model/enumerations/offer-type.model';
import { OfferPurchaseLimitation } from 'app/shared/model/enumerations/offer-purchase-limitation.model';
import { NatureOFOffer } from 'app/shared/model/enumerations/nature-of-offer.model';
import { OfferValidity } from 'app/shared/model/enumerations/offer-validity.model';
import { RiskOfMarketShareLoss } from 'app/shared/model/enumerations/risk-of-market-share-loss.model';

export interface IForm {
  id?: number;
  competitor?: CompetitorName;
  province?: Province;
  imageContentType?: string;
  image?: any;
  offerDate?: Moment;
  productType?: ProductType;
  typeOfOffer?: TypeOfOffer;
  offerType?: OfferType;
  offerPurchaseLimitation?: OfferPurchaseLimitation;
  natureOFOffer?: NatureOFOffer;
  offerPrice?: number;
  offerValidity?: OfferValidity;
  offerAllocation?: number;
  offerEndDate?: Moment;
  riskOfMarketShareLoss?: RiskOfMarketShareLoss;
}

export class Form implements IForm {
  constructor(
    public id?: number,
    public competitor?: CompetitorName,
    public province?: Province,
    public imageContentType?: string,
    public image?: any,
    public offerDate?: Moment,
    public productType?: ProductType,
    public typeOfOffer?: TypeOfOffer,
    public offerType?: OfferType,
    public offerPurchaseLimitation?: OfferPurchaseLimitation,
    public natureOFOffer?: NatureOFOffer,
    public offerPrice?: number,
    public offerValidity?: OfferValidity,
    public offerAllocation?: number,
    public offerEndDate?: Moment,
    public riskOfMarketShareLoss?: RiskOfMarketShareLoss
  ) {}
}
