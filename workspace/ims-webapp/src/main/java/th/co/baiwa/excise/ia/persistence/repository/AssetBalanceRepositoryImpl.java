package th.co.baiwa.excise.ia.persistence.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;

import th.co.baiwa.buckwaframework.common.constant.CommonConstants.FLAG;
import th.co.baiwa.buckwaframework.common.persistence.jdbc.CommonJdbcTemplate;
import th.co.baiwa.excise.ia.persistence.entity.AssetBalance;
import th.co.baiwa.excise.ia.persistence.repository.AssetBalanceRepositoryCustom;
import th.co.baiwa.excise.utils.BeanUtils;

public class AssetBalanceRepositoryImpl implements AssetBalanceRepositoryCustom {
	private static final Logger logger = LoggerFactory.getLogger(AssetBalanceRepositoryImpl.class);

	private final CommonJdbcTemplate commonJdbcTemplate;

	@Autowired
	public AssetBalanceRepositoryImpl(CommonJdbcTemplate commonJdbcTemplate) {
		this.commonJdbcTemplate = commonJdbcTemplate;
	}

	@Override
	public List<AssetBalance> findAssetBalanceByCriteria(AssetBalance assetBalance) {
		logger.info("findAssetBalanceByCriteria");
		List<Object> params = new ArrayList<Object>();
		StringBuilder sql = new StringBuilder("SELECT * FROM IA_ASSET_BALANCE WHERE IS_DELETED = ? ");
		params.add(FLAG.N_FLAG);
		if (BeanUtils.isNotEmpty(assetBalance.getAssetBalanceId())) {
			sql.append(" AND ASSET_BALANCE_ID = ? ");
			params.add(assetBalance.getAssetBalanceId());
		}
		if (BeanUtils.isNotEmpty(assetBalance.getExciseDepartment())) {
			sql.append(" AND EXCISE_DEPARTMENT = ? ");
			params.add(assetBalance.getExciseDepartment());
		}
		if (BeanUtils.isNotEmpty(assetBalance.getExciseRegion())) {
			sql.append(" AND EXCISE_REGION = ? ");
			params.add(assetBalance.getExciseRegion());
		}
		if (BeanUtils.isNotEmpty(assetBalance.getExciseDistrict())) {
			sql.append(" AND EXCISE_DISTRICT = ? ");
			params.add(assetBalance.getExciseDistrict());
		}
		if (BeanUtils.isNotEmpty(assetBalance.getGovernmentSector())) {
			sql.append(" AND GOVERNMENT_SECTOR = ? ");
			params.add(assetBalance.getGovernmentSector());
		}
		if (BeanUtils.isNotEmpty(assetBalance.getInstitute())) {
			sql.append(" AND INSTITUTE = ? ");
			params.add(assetBalance.getInstitute());
		}
		if (BeanUtils.isNotEmpty(assetBalance.getAssetType())) {
			sql.append(" AND ASSET_TYPE = ? ");
			params.add(assetBalance.getAssetType());
		}
		if (BeanUtils.isNotEmpty(assetBalance.getAssetCode())) {
			sql.append(" AND ASSET_CODE = ? "); 
			params.add(assetBalance.getAssetCode());
		}
		if (BeanUtils.isNotEmpty(assetBalance.getAssetFeature())) {
			sql.append(" AND ASSET_FEATURE = ? ");
			params.add(assetBalance.getAssetFeature());
		}
		if (BeanUtils.isNotEmpty(assetBalance.getAssetModel())) {
			sql.append(" AND ASSET_MODEL = ? ");
			params.add(assetBalance.getAssetModel());
		}
		if (BeanUtils.isNotEmpty(assetBalance.getPicAddress())) {
			sql.append(" AND PIC_ADDRESS = ? ");
			params.add(assetBalance.getPicAddress());
		}
		if (BeanUtils.isNotEmpty(assetBalance.getVendorsName())) {
			sql.append(" AND VENDORS_NAME = ? ");
			params.add(assetBalance.getVendorsName());
		}
		if (BeanUtils.isNotEmpty(assetBalance.getVendorsAddress())) {
			sql.append(" AND VENDORS_ADDRESS = ? ");
			params.add(assetBalance.getVendorsAddress());
		}
		if (BeanUtils.isNotEmpty(assetBalance.getVendorsPhone())) {
			sql.append(" AND VENDORS_PHONE = ? ");
			params.add(assetBalance.getVendorsPhone());
		}
		if (BeanUtils.isNotEmpty(assetBalance.getTypeCost())) {
			sql.append(" AND TYPE_COST = ? ");
			params.add(assetBalance.getTypeCost());
		}
		if (BeanUtils.isNotEmpty(assetBalance.getAcquisition())) {
			sql.append(" AND ACQUISITION = ? ");
			params.add(assetBalance.getAcquisition());
		}
		if (BeanUtils.isNotEmpty(assetBalance.getDocumentDate())) {
			sql.append(" AND DOCUMENT_DATE = ? ");
			params.add(assetBalance.getDocumentDate());
		}
		if (BeanUtils.isNotEmpty(assetBalance.getDocumentNo())) {
			sql.append(" AND DOCUMENT_NO = ? ");
			params.add(assetBalance.getDocumentNo());
		}
		if (BeanUtils.isNotEmpty(assetBalance.getAssetDescription())) {
			sql.append(" AND ASSET_DESCRIPTION = ? ");
			params.add(assetBalance.getAssetDescription());
		}
		if (BeanUtils.isNotEmpty(assetBalance.getAssetAmount())) {
			sql.append(" AND ASSET_AMOUNT = ? ");
			params.add(assetBalance.getAssetAmount());
		}
		if (BeanUtils.isNotEmpty(assetBalance.getUnitPriceAsset())) {
			sql.append(" AND UNIT_PRICE_ASSET = ? ");
			params.add(assetBalance.getUnitPriceAsset());
		}
		if (BeanUtils.isNotEmpty(assetBalance.getTotlePriceAsset())) {
			sql.append(" AND TOTLE_PRICE_ASSET = ? ");
			params.add(assetBalance.getTotlePriceAsset());
		}
		if (BeanUtils.isNotEmpty(assetBalance.getLifetimeAsset())) {
			sql.append(" AND LIFETIME_ASSET = ? ");
			params.add(assetBalance.getLifetimeAsset());
		}
		if (BeanUtils.isNotEmpty(assetBalance.getDateOfManufacture())) {
			sql.append(" AND DATE_OF_MANUFACTURE = ? ");
			params.add(assetBalance.getDateOfManufacture());
		}
		if (BeanUtils.isNotEmpty(assetBalance.getDepreciationRate())) {
			sql.append(" AND DEPRECIATION_RATE = ? ");
			params.add(assetBalance.getDepreciationRate());
		}
		if (BeanUtils.isNotEmpty(assetBalance.getAnnualDepreciation())) {
			sql.append(" AND ANNUAL_DEPRECIATION = ? ");
			params.add(assetBalance.getAnnualDepreciation());
		}
		if (BeanUtils.isNotEmpty(assetBalance.getAccumulatedDepreciation())) {
			sql.append(" AND ACCUMULATED_DEPRECIATION = ? ");
			params.add(assetBalance.getAccumulatedDepreciation());
		}
		if (BeanUtils.isNotEmpty(assetBalance.getNetValue())) {
			sql.append(" AND NET_VALUE = ? ");
			params.add(assetBalance.getNetValue());
		}
		if (BeanUtils.isNotEmpty(assetBalance.getAssetNote())) {
			sql.append(" AND ASSET_NOTE = ? ");
			params.add(assetBalance.getAssetNote());
		}

		return commonJdbcTemplate.executeQuery(sql.toString(), params.toArray(), mapping);
	}

	private RowMapper<AssetBalance> mapping = new RowMapper<AssetBalance>() {

		@Override
		public AssetBalance mapRow(ResultSet rs, int rowNum) throws SQLException {

			AssetBalance assInfHdr = new AssetBalance();
			assInfHdr.setAssetBalanceId(rs.getLong("ASSET_BALANCE_ID"));
			assInfHdr.setExciseDepartment(rs.getString("EXCISE_DEPARTMENT"));
			assInfHdr.setExciseRegion(rs.getString("EXCISE_REGION"));
			assInfHdr.setExciseDistrict(rs.getString("EXCISE_DISTRICT"));
			assInfHdr.setGovernmentSector(rs.getString("GOVERNMENT_SECTOR"));
			assInfHdr.setInstitute(rs.getString("INSTITUTE"));
			assInfHdr.setAssetType(rs.getString("ASSET_TYPE"));
			assInfHdr.setAssetCode(rs.getString("ASSET_CODE"));
			assInfHdr.setAssetFeature(rs.getString("ASSET_FEATURE"));
			assInfHdr.setAssetModel(rs.getString("ASSET_MODEL"));
			assInfHdr.setPicAddress(rs.getString("PIC_ADDRESS"));
			assInfHdr.setVendorsName(rs.getString("VENDORS_NAME"));
			assInfHdr.setVendorsAddress(rs.getString("VENDORS_ADDRESS"));
			assInfHdr.setVendorsPhone(rs.getString("VENDORS_PHONE"));
			assInfHdr.setTypeCost(rs.getString("TYPE_COST"));
			assInfHdr.setAcquisition(rs.getString("ACQUISITION"));
			assInfHdr.setDocumentDate(rs.getDate("DOCUMENT_DATE"));
			assInfHdr.setDocumentNo(rs.getString("DOCUMENT_NO"));
			assInfHdr.setAssetDescription(rs.getString("ASSET_DESCRIPTION"));
			assInfHdr.setAssetAmount(rs.getBigDecimal("ASSET_AMOUNT"));
			assInfHdr.setUnitPriceAsset(rs.getBigDecimal("UNIT_PRICE_ASSET"));
			assInfHdr.setTotlePriceAsset(rs.getBigDecimal("TOTLE_PRICE_ASSET"));
			assInfHdr.setLifetimeAsset(rs.getBigDecimal("LIFETIME_ASSET"));
			assInfHdr.setDateOfManufacture(rs.getDate("DATE_OF_MANUFACTURE"));
			assInfHdr.setDepreciationRate(rs.getBigDecimal("DEPRECIATION_RATE"));
			assInfHdr.setAnnualDepreciation(rs.getBigDecimal("ANNUAL_DEPRECIATION"));
			assInfHdr.setAccumulatedDepreciation(rs.getBigDecimal("ACCUMULATED_DEPRECIATION"));
			assInfHdr.setNetValue(rs.getBigDecimal("NET_VALUE"));
			assInfHdr.setAssetNote(rs.getString("ASSET_NOTE"));
			assInfHdr.setCreatedBy(rs.getString("CREATED_BY"));
			assInfHdr.setCreatedDate(rs.getDate("CREATED_DATE"));
			assInfHdr.setUpdatedBy(rs.getString("UPDATED_BY"));
			assInfHdr.setUpdatedDate(rs.getDate("UPDATED_DATE"));
			assInfHdr.setIsDeleted(rs.getString("IS_DELETED"));
			assInfHdr.setVersion(rs.getInt("VERSION"));

			return assInfHdr;
		}
	};

}
