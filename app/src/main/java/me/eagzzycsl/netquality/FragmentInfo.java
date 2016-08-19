package me.eagzzycsl.netquality;

public class FragmentInfo extends BaseFragment {
    @Override
    public AdapterInfo getAdapter() {
        return new AdapterInfo(getActivity());
    }
}
