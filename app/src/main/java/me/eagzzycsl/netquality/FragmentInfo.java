package me.eagzzycsl.netquality;

public class FragmentInfo extends BaseFragment<DatumInfo> {
    @Override
    public AdapterInfo getAdapter() {
        return new AdapterInfo(getActivity());
    }

}
