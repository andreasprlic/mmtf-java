package org.rcsb.mmtf.decoder;

import java.util.ArrayList;
import java.util.List;

import org.rcsb.mmtf.api.StructureDataInterface;
import org.rcsb.mmtf.codec.CharCodecs;
import org.rcsb.mmtf.codec.FloatCodecs;
import org.rcsb.mmtf.codec.IntCodecs;
import org.rcsb.mmtf.codec.StringCodecs;
import org.rcsb.mmtf.dataholders.BioAssemblyData;
import org.rcsb.mmtf.dataholders.Entity;
import org.rcsb.mmtf.dataholders.Group;
import org.rcsb.mmtf.dataholders.MmtfStructure;

/**
 * A genric decoder of MMTF data - that does not hard code the encoding strategy.
 * @author Anthony Bradley
 *
 */
public class GenericDecoder  implements StructureDataInterface {

	/**
	 * Constructor for the default decoder.
	 * @param inputData The input mmtfBean data to be decompressed.
	 */
	public GenericDecoder(MmtfStructure inputData) {
		// Decode the coordinate  and B-factor arrays.
		cartnX = FloatCodecs.decodeArr(inputData.getxCoordList());
		cartnY = FloatCodecs.decodeArr(inputData.getyCoordList());
		cartnZ = FloatCodecs.decodeArr(inputData.getzCoordList());
		bFactor = FloatCodecs.decodeArr(inputData.getbFactorList());
		// Run length decode the occupancy array
		occupancy = FloatCodecs.decodeArr(inputData.getOccupancyList());
		// Run length and delta 
		atomId = IntCodecs.decodeArr(inputData.getAtomIdList());
		// Run length encoded
		altId = CharCodecs.decodeArr(inputData.getAltLocList());
		insertionCodeList = CharCodecs.decodeArr(inputData.getInsCodeList());
		// Get the groupNumber
		groupNum = IntCodecs.decodeArr(inputData.getGroupIdList());
		// Get the group map (all the unique groups in the structure).
		groupMap = inputData.getGroupList();
		// Get the seqRes groups
		seqResGroupList = IntCodecs.decodeArr(inputData.getSequenceIndexList());
		groupList = IntCodecs.decodeArr(inputData.getGroupTypeList());
		// Get the internal and public facing chain ids
		publicChainIds = StringCodecs.decodeArr(inputData.getChainNameList());
		chainList =  StringCodecs.decodeArr(inputData.getChainIdList());
		interGroupBondIndices = IntCodecs.decodeArr(inputData.getBondAtomList());
		interGroupBondOrders = IntCodecs.decodeArr(inputData.getBondOrderList());
		secStructInfo = IntCodecs.decodeArr(inputData.getSecStructList());
		// Get the number of chains per model
		chainsPerModel = inputData.getChainsPerModel();
		groupsPerChain = inputData.getGroupsPerChain();
		spaceGroup = inputData.getSpaceGroup();
		unitCell = inputData.getUnitCell();
		bioAssembly  = inputData.getBioAssemblyList();
		mmtfVersion = inputData.getMmtfVersion();
		mmtfProducer = inputData.getMmtfProducer();
		entityList = inputData.getEntityList();
		pdbId = inputData.getStructureId();
		// Now get the header data
		rFree = inputData.getrFree();
		// Optional fields
		rWork = inputData.getrWork();
		resolution = inputData.getResolution();
		title = inputData.getTitle();
		experimentalMethods = inputData.getExperimentalMethods();
		// Now get the relase information
		depositionDate = inputData.getDepositionDate();
		releaseDate = inputData.getReleaseDate();
		numGroups = inputData.getNumGroups();
		numChains = inputData.getNumChains();
		numModels = inputData.getNumModels();
		ncsOperMatrixList = inputData.getNcsOperatorList();
	}

	/** The X coordinates */
	private float[] cartnX;

	/** The Y coordinates */
	private float[] cartnY;

	/** The Z coordinates */
	private float[] cartnZ;

	/** The X coordinates */
	private float[] bFactor;

	/** The Y coordinates */
	private float[] occupancy;

	/** The atom id. */
	private int[] atomId;

	/** The alt id. */
	private char[] altId;

	/** The ins code. */
	private char[] insertionCodeList;

	/** The group num. */
	private int[] groupNum;

	/** The group map. */
	private Group[] groupMap;

	/** The group list. */
	private int[] groupList;

	/** The sequence ids of the groups */
	private int[] seqResGroupList;

	/** The public facing chain ids*/
	private String[] publicChainIds;

	/** The number of chains per model*/
	private int[] chainsPerModel;

	/** The number of groups per (internal) chain*/
	private int[] groupsPerChain;

	/** The space group of the structure*/
	private String spaceGroup;

	/** The unit cell of the structure*/
	private float[] unitCell;

	/** The bioassembly information for the structure*/
	private List<BioAssemblyData> bioAssembly;

	/** The bond indices for bonds between groups*/
	private int[] interGroupBondIndices;

	/** The bond orders for bonds between groups*/
	private int[] interGroupBondOrders;

	/** The chosen list of chain ids */
	private String[] chainList;

	/** The mmtf version */
	private String mmtfVersion;

	/** The mmtf prodcuer */
	private String mmtfProducer;

	/** A list containing pdb group names for nucleic acids */
	List<String> nucAcidList = new ArrayList<>();

	/** The list of entities in this structure. */
	private Entity[] entityList;

	/** The PDB id	 */
	private String pdbId;

	/** The reported resolution of the dataset. */
	private Float resolution;

	/** The reported R Free of the model. */
	private Float rFree;

	/** The reported R Work of the model. */
	private Float rWork;

	/** The title of the model. */
	private String title;

	/** The list of experimental methods. */
	private String[] experimentalMethods;

	/** The deposition date of the structure */
	private String depositionDate;

	/** The release date of the  structure */
	private String releaseDate;

	private int[] secStructInfo;

	private int numModels;
	
	private int numChains;
	
	private int numGroups;
	
	private double[][] ncsOperMatrixList;



	@Override
	public float[] getxCoords() {
		return cartnX;
	}

	@Override
	public float[] getyCoords() {
		return cartnY;
	}

	@Override
	public float[] getzCoords() {
		return cartnZ;
	}

	@Override
	public float[] getbFactors() {
		return bFactor;
	}

	@Override
	public float[] getOccupancies() {
		return occupancy;
	}

	@Override
	public int[] getAtomIds() {
		return atomId;
	}

	@Override
	public char[] getAltLocIds() {
		return altId;
	}

	@Override
	public char[] getInsCodes() {
		return insertionCodeList;
	}

	@Override
	public int[] getGroupIds() {
		return groupNum;
	}

	@Override
	public int[] getGroupTypeIndices() {
		return groupList;
	}

	@Override
	public int[] getGroupSequenceIndices() {
		return seqResGroupList;
	}

	@Override
	public String[] getChainNames() {
		return publicChainIds;
	}

	@Override
	public int[] getChainsPerModel() {
		return chainsPerModel;
	}

	@Override
	public int[] getGroupsPerChain() {
		return groupsPerChain;
	}

	@Override
	public String getSpaceGroup() {
		return spaceGroup;
	}

	@Override
	public float[] getUnitCell() {
		return unitCell;
	}

	@Override
	public int[] getInterGroupBondIndices() {
		return interGroupBondIndices;
	}

	@Override
	public int[] getInterGroupBondOrders() {
		return interGroupBondOrders;
	}

	@Override
	public String[] getChainIds() {
		return chainList;
	}

	@Override
	public String getMmtfVersion() {
		return mmtfVersion;
	}

	@Override
	public String getMmtfProducer() {
		return mmtfProducer;
	}

	@Override
	public String getStructureId() {
		return pdbId;
	}

	@Override
	public int getNumGroups() {
		return numGroups;
	}

	@Override
	public int getNumChains() {
		return numChains;
	}

	@Override
	public int getNumModels() {	
		return numModels;
	}

	@Override
	public int getNumAtoms() {
		return this.cartnX.length;
	}

	@Override
	public float getRfree() {
		if (rFree==null|| rFree ==0.0f) {
			return MmtfStructure.UNAVAILABLE_R_VALUE;
		}
		return rFree;
	}

	@Override
	public float getResolution() {
		if (resolution==null || resolution==0.0f) {
			return MmtfStructure.UNAVAILABLE_RESOLUTION_VALUE;
		}
		return resolution;
	}

	@Override
	public float getRwork() {
		if (rWork==null|| rWork ==0.0f) {
			return MmtfStructure.UNAVAILABLE_R_VALUE;
		}
		return rWork;
	}

	@Override
	public String getTitle() {
		return title;
	}

	@Override
	public String[] getExperimentalMethods() {
		return experimentalMethods;
	}

	@Override
	public String getGroupName(int groupInd) {
		return groupMap[groupInd].getGroupName();
	}

	public int getNumAtomsInGroup(int groupInd) {
		return groupMap[groupInd].getFormalChargeList().length;
	}

	@Override
	public String[] getGroupAtomNames(int groupInd) {
		return groupMap[groupInd].getAtomNameList();
	}

	@Override
	public String[] getGroupElementNames(int groupInd) {
		return groupMap[groupInd].getElementList();

	}

	@Override
	public int[] getGroupBondOrders(int groupInd) {
		return groupMap[groupInd].getBondOrderList();

	}

	@Override
	public int[] getGroupBondIndices(int groupInd) {
		return groupMap[groupInd].getBondAtomList();
	}

	@Override
	public int[] getGroupAtomCharges(int groupInd) {
		return groupMap[groupInd].getFormalChargeList();
	}

	@Override
	public char getGroupSingleLetterCode(int groupInd) {
		return groupMap[groupInd].getSingleLetterCode();
	}

	@Override
	public String getGroupChemCompType(int groupInd) {
		return groupMap[groupInd].getChemCompType();
	}

	@Override
	public String getEntityDescription(int entityInd) {
		return entityList[entityInd].getDescription();
	}

	@Override
	public String getEntityType(int entityInd) {
		return entityList[entityInd].getType();

	}

	@Override
	public int[] getEntityChainIndexList(int entityInd) {
		return entityList[entityInd].getChainIndexList();

	}

	@Override
	public String getEntitySequence(int entityInd) {
		return entityList[entityInd].getSequence();

	}

	@Override
	public int getNumEntities() {
		return entityList.length;
	}

	@Override
	public int getNumBioassemblies() {
		return bioAssembly.size();
	}

	@Override
	public int getNumTransInBioassembly(int bioassemblyIndex) {
		return bioAssembly.get(bioassemblyIndex).getTransformList().size();
	}

	@Override
	public int[] getChainIndexListForTransform(int bioassemblyIndex, int transformationIndex) {
		return bioAssembly.get(bioassemblyIndex).getTransformList().get(transformationIndex).getChainIndexList();
	}

	@Override
	public double[] getMatrixForTransform(int bioassemblyIndex, int transformationIndex) {
		return bioAssembly.get(bioassemblyIndex).getTransformList().get(transformationIndex).getMatrix();
	}

	@Override
	public String getDepositionDate() {
		return depositionDate;
	}

	@Override
	public int getNumBonds() {
		int numIntergroupBonds = interGroupBondOrders.length;
		for(int groupIndex : groupList) {
			numIntergroupBonds+=groupMap[groupIndex].getBondOrderList().length;
		}
		return numIntergroupBonds;
	}

	@Override
	public int[] getSecStructList() {
		return secStructInfo;
	}

	@Override
	public String getReleaseDate() {
		return releaseDate;
	}

	@Override
	public double[][] getNcsOperatorList() {
		return ncsOperMatrixList;
	}

	@Override
	public String getBioassemblyName(int bioassemblyIndex) {
		return bioAssembly.get(bioassemblyIndex).getName();
	}

}
