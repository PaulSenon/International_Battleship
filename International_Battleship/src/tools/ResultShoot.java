package tools;

public enum ResultShoot {
	TOUCHED(FxType.EXPLOSION),
	MISSED(FxType.SPLASH),
	DESTROYED(FxType.EXPLOSION),
	ALREADY_TOUCHED(FxType.EXPLOSION),
	FORBIDDEN(null);

	public FxType fxType;
	ResultShoot(FxType type){
		this.fxType = type;
	}
}
