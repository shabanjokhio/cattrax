package com.cattrax.domain;

import com.cattrax.domain.enums.*;

import javax.persistence.Entity;

//@Embeddable
@Entity
public class CPACScoreMeasurement extends AbstractDomainEntity{

	private BCVAValue bcvaLine_OD;
	private int bcvaLetter_OD;
	private BCVAValue bcvaLine_OU;
	private int bcvaLetter_OU;
	private BCVAValue bcvaLine_OS;
	private int bcvaLetter_OS;
	private float sphere_OD;
	private float cyl_OD;
	private int axis_OD;
	private float sphere_OS;
	private float cyl_OS;
	private int axis_OS;
	private boolean noImprovement_OD;
	private boolean noImprovement_OS;
	private LensStatus lensStatus_OD;
	private LensStatus lensStatus_OS;
	private NuclearColour nucC_OD;
	private NuclearColour nucC_OS;
	private NuclearOpalescence nucO_OD;
	private NuclearOpalescence nucO_OS;
	private Cortical cort_OD;
	private Cortical cort_OS;
	private PostSubcapsular psc_OD;
	private PostSubcapsular psc_OS;
	private VisualPotential visualPotential_OD;
	private VisualPotential visualPotential_OS;
	private boolean glare_OD;
	private boolean glare_OS;
	private boolean myopicShift_OD;
	private boolean myopicShift_OS;

	public BCVAValue getBcvaLine_OD() {
		return bcvaLine_OD;
	}

	public void setBcvaLine_OD(BCVAValue bcvaLine_OD) {
		this.bcvaLine_OD = bcvaLine_OD;
	}

	public int getBcvaLetter_OD() {
		return bcvaLetter_OD;
	}

	public void setBcvaLetter_OD(int bcvaLetter_OD) {
		this.bcvaLetter_OD = bcvaLetter_OD;
	}

	public BCVAValue getBcvaLine_OU() {
		return bcvaLine_OU;
	}

	public void setBcvaLine_OU(BCVAValue bcvaLine_OU) {
		this.bcvaLine_OU = bcvaLine_OU;
	}

	public int getBcvaLetter_OU() {
		return bcvaLetter_OU;
	}

	public void setBcvaLetter_OU(int bcvaLetter_OU) {
		this.bcvaLetter_OU = bcvaLetter_OU;
	}

	public BCVAValue getBcvaLine_OS() {
		return bcvaLine_OS;
	}

	public void setBcvaLine_OS(BCVAValue bcvaLine_OS) {
		this.bcvaLine_OS = bcvaLine_OS;
	}

	public int getBcvaLetter_OS() {
		return bcvaLetter_OS;
	}

	public void setBcvaLetter_OS(int bcvaLetter_OS) {
		this.bcvaLetter_OS = bcvaLetter_OS;
	}

	public float getSphere_OD() {
		return sphere_OD;
	}

	public void setSphere_OD(float sphere_OD) {
		this.sphere_OD = sphere_OD;
	}

	public float getCyl_OD() {
		return cyl_OD;
	}

	public void setCyl_OD(float cyl_OD) {
		this.cyl_OD = cyl_OD;
	}

	public int getAxis_OD() {
		return axis_OD;
	}

	public void setAxis_OD(int axis_OD) {
		this.axis_OD = axis_OD;
	}

	public float getSphere_OS() {
		return sphere_OS;
	}

	public void setSphere_OS(float sphere_OS) {
		this.sphere_OS = sphere_OS;
	}

	public float getCyl_OS() {
		return cyl_OS;
	}

	public void setCyl_OS(float cyl_OS) {
		this.cyl_OS = cyl_OS;
	}

	public int getAxis_OS() {
		return axis_OS;
	}

	public void setAxis_OS(int axis_OS) {
		this.axis_OS = axis_OS;
	}

	public boolean isNoImprovement_OD() {
		return noImprovement_OD;
	}

	public void setNoImprovement_OD(boolean noImprovement_OD) {
		this.noImprovement_OD = noImprovement_OD;
	}

	public boolean isNoImprovement_OS() {
		return noImprovement_OS;
	}

	public void setNoImprovement_OS(boolean noImprovement_OS) {
		this.noImprovement_OS = noImprovement_OS;
	}

	public LensStatus getLensStatus_OD() {
		return lensStatus_OD;
	}

	public void setLensStatus_OD(LensStatus lensStatus_OD) {
		this.lensStatus_OD = lensStatus_OD;
	}

	public LensStatus getLensStatus_OS() {
		return lensStatus_OS;
	}

	public void setLensStatus_OS(LensStatus lensStatus_OS) {
		this.lensStatus_OS = lensStatus_OS;
	}

	public NuclearColour getNucC_OD() {
		return nucC_OD;
	}

	public void setNucC_OD(NuclearColour nucC_OD) {
		this.nucC_OD = nucC_OD;
	}

	public NuclearColour getNucC_OS() {
		return nucC_OS;
	}

	public void setNucC_OS(NuclearColour nucC_OS) {
		this.nucC_OS = nucC_OS;
	}

	public NuclearOpalescence getNucO_OD() {
		return nucO_OD;
	}

	public void setNucO_OD(NuclearOpalescence nucO_OD) {
		this.nucO_OD = nucO_OD;
	}

	public NuclearOpalescence getNucO_OS() {
		return nucO_OS;
	}

	public void setNucO_OS(NuclearOpalescence nucO_OS) {
		this.nucO_OS = nucO_OS;
	}

	public Cortical getCort_OD() {
		return cort_OD;
	}

	public void setCort_OD(Cortical cort_OD) {
		this.cort_OD = cort_OD;
	}

	public Cortical getCort_OS() {
		return cort_OS;
	}

	public void setCort_OS(Cortical cort_OS) {
		this.cort_OS = cort_OS;
	}

	public PostSubcapsular getPsc_OD() {
		return psc_OD;
	}

	public void setPsc_OD(PostSubcapsular psc_OD) {
		this.psc_OD = psc_OD;
	}

	public PostSubcapsular getPsc_OS() {
		return psc_OS;
	}

	public void setPsc_OS(PostSubcapsular psc_OS) {
		this.psc_OS = psc_OS;
	}

	public VisualPotential getVisualPotential_OD() {
		return visualPotential_OD;
	}

	public void setVisualPotential_OD(VisualPotential visualPotential_OD) {
		this.visualPotential_OD = visualPotential_OD;
	}

	public VisualPotential getVisualPotential_OS() {
		return visualPotential_OS;
	}

	public void setVisualPotential_OS(VisualPotential visualPotential_OS) {
		this.visualPotential_OS = visualPotential_OS;
	}

	public boolean isGlare_OD() {
		return glare_OD;
	}

	public void setGlare_OD(boolean glare_OD) {
		this.glare_OD = glare_OD;
	}

	public boolean isGlare_OS() {
		return glare_OS;
	}

	public void setGlare_OS(boolean glare_OS) {
		this.glare_OS = glare_OS;
	}

	public boolean isMyopicShift_OD() {
		return myopicShift_OD;
	}

	public void setMyopicShift_OD(boolean myopicShift_OD) {
		this.myopicShift_OD = myopicShift_OD;
	}

	public boolean isMyopicShift_OS() {
		return myopicShift_OS;
	}

	public void setMyopicShift_OS(boolean myopicShift_OS) {
		this.myopicShift_OS = myopicShift_OS;
	}

	@Override
	public String toString() {
		return "CPACScoreMeasurement{" +
				"bcvaLine_OD=" + bcvaLine_OD +
				", bcvaLetter_OD=" + bcvaLetter_OD +
				", bcvaLine_OU=" + bcvaLine_OU +
				", bcvaLetter_OU=" + bcvaLetter_OU +
				", bcvaLine_OS=" + bcvaLine_OS +
				", bcvaLetter_OS=" + bcvaLetter_OS +
				", sphere_OD=" + sphere_OD +
				", cyl_OD=" + cyl_OD +
				", axis_OD=" + axis_OD +
				", sphere_OS=" + sphere_OS +
				", cyl_OS=" + cyl_OS +
				", axis_OS=" + axis_OS +
				", noImprovement_OD=" + noImprovement_OD +
				", noImprovement_OS=" + noImprovement_OS +
				", lensStatus_OD=" + lensStatus_OD +
				", lensStatus_OS=" + lensStatus_OS +
				", nucC_OD=" + nucC_OD +
				", nucC_OS=" + nucC_OS +
				", nucO_OD=" + nucO_OD +
				", nucO_OS=" + nucO_OS +
				", cort_OD=" + cort_OD +
				", cort_OS=" + cort_OS +
				", psc_OD=" + psc_OD +
				", psc_OS=" + psc_OS +
				", visualPotential_OD=" + visualPotential_OD +
				", visualPotential_OS=" + visualPotential_OS +
				", glare_OD=" + glare_OD +
				", glare_OS=" + glare_OS +
				", myopicShift_OD=" + myopicShift_OD +
				", myopicShift_OS=" + myopicShift_OS +
				'}';
	}
}
