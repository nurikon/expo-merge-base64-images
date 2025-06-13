import { requireNativeView } from 'expo';
import * as React from 'react';

import { ExpoMergeBase64ImagesViewProps } from './ExpoMergeBase64Images.types';

const NativeView: React.ComponentType<ExpoMergeBase64ImagesViewProps> =
  requireNativeView('ExpoMergeBase64Images');

export default function ExpoMergeBase64ImagesView(props: ExpoMergeBase64ImagesViewProps) {
  return <NativeView {...props} />;
}
